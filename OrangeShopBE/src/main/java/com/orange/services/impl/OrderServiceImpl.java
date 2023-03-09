package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.model.*;
import com.orange.domain.model.GHN.GHNItem;
import com.orange.domain.model.GHN.GHNShippingOrder;
import com.orange.exception.*;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.dto.OrderDTO;
import com.orange.payload.request.UpdateOrderStatus;
import com.orange.repositories.IOrderDetailRepository;
import com.orange.repositories.IOrderRepository;
import com.orange.repositories.IProductDetailRepository;
import com.orange.repositories.IProductRepository;
import com.orange.services.IOrderService;
import com.orange.services.IShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderMapper orderMapper;
    private final IOrderViewMapper orderViewMapper;
    private final IShippingService shippingService;
    private final IProductDetailRepository productDetailRepository;


    @Override
    public OrderDTO create(OrderDTO dto) {

        dto.setOrderStatus(new OrderStatus(Long.valueOf(EOrderStatus.WAIT_FOR_CONFIRMATION.getId())));

        Order result = orderRepository.save(orderMapper.toEntity(dto));
        Set<OrderDetail> orderDetails = result.getOrderDetails();

        List<Long> productIds = orderDetails.stream().map(OrderDetail::getProductDetail).map(ProductDetail::getId).collect(Collectors.toList());

        Map<Long, ProductDetail> productDetailMap = productDetailRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(ProductDetail::getId, Function.identity()));

        List<OrderDetail> updatedOrderDetails = orderDetails.stream().map(orderDetail -> {
            Long productId = orderDetail.getProductDetail().getId();
            Integer quantity = orderDetail.getQuantity();

            ProductDetail productDetail = productDetailMap.get(productId);

            if (productDetail == null) {
                throw new EntityNotFoundException("Không tìm thấy sản phẩm có ID: " + productId);
            }

            if (productDetail.getQuantity() < quantity) {
                throw new NotEnoughStockException("Số lượng trong kho không đủ");
            }

            productDetail.setQuantity(productDetail.getQuantity() - quantity);
            orderDetail.setProductDetail(productDetail);
            orderDetail.setOrder(result);

            return orderDetail;
        }).collect(Collectors.toList());

        orderDetailRepository.saveAll(updatedOrderDetails);
        productDetailRepository.saveAll(productDetailMap.values());
        result.setOrderDetails(new HashSet<>(updatedOrderDetails));

        return orderMapper.toDto(result);
    }


    @Override
    public OrderDTO update(OrderDTO dto) {

        return null;
    }

    @Override
    public OrderDTO delete(OrderDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        org.springframework.data.domain.Page<Order> result = this.orderRepository.findAll(pageable);
        List<OrderViewDTO> viewDTOList = result.getContent()
                .stream()
                .map(orderViewMapper::toDto)
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(), result.getTotalPages(), Math.toIntExact(result.getTotalElements()), viewDTOList);
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = getOrderById(id);
        return orderMapper.toDto(order);
    }

    private Order getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "Không tìm thấy Order!"));
    }

    @Transactional
    @Override
    public OrderDTO updateOrderStatus(UpdateOrderStatus orderStatus) {
        if (orderStatus.getOrderId() == null) {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_EXCEPTION, "Không được để trống id");
        }
        if (orderStatus.getOrderStatusId() == null) {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_EXCEPTION, "Không được để trống trạng thái");
        }

        try {
            Order order = this.getOrderById(orderStatus.getOrderId());
            order.setOrderStatus(new OrderStatus(orderStatus.getOrderStatusId()));
            if (orderStatus.getOrderStatusId() == EOrderStatus.CONFIRMED.getId()) {
                GHNShippingOrder ghnShippingOrder = new GHNShippingOrder();
                List<GHNItem> items = order.getOrderDetails().stream()
                                .map(od -> GHNItem.builder()
                                        .name(od.getProductDetail().getProduct().getName())
                                        .quantity(od.getQuantity())
                                        .build()).collect(Collectors.toList());
                ghnShippingOrder.setTo_name(order.getConsigneeName());
                ghnShippingOrder.setTo_phone(order.getConsigneePhone());
                ghnShippingOrder.setTo_address(order.getAddress().getAddressLine1());
                ghnShippingOrder.setTo_ward_name(order.getAddress().getVillage().getName());
                ghnShippingOrder.setTo_district_name(order.getAddress().getVillage().getDistrict().getName());
                ghnShippingOrder.setTo_province_name(order.getAddress().getVillage().getDistrict().getCity().getName());
                ghnShippingOrder.setWeight(200);
                ghnShippingOrder.setLength(1);
                ghnShippingOrder.setWidth(19);
                ghnShippingOrder.setHeight(10);
                ghnShippingOrder.setService_type_id(2);
                ghnShippingOrder.setService_id(0);
                ghnShippingOrder.setPayment_type_id(1);
                ghnShippingOrder.setRequired_note("KHONGCHOXEMHANG");
                ghnShippingOrder.setItems(items);

                String shippingCode = this.shippingService.createShippingOrder(ghnShippingOrder).getData().getOrder_code();
                order.setShippingCode(shippingCode);
            }
            return orderMapper.toDto(this.orderRepository.save(order));
        } catch (DataAccessException e) {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "Có lỗi xảy ra trong quá trình cập nhật trạng thái");
        }
    }
}
