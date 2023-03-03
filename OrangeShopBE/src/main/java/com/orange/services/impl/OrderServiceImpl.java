package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.model.*;
import com.orange.exception.EntityNotFoundException;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.dto.OrderDTO;
import com.orange.exception.NotEnoughStockException;
import com.orange.exception.OrderUpdateException;
import com.orange.payload.request.UpdateOrderStatus;
import com.orange.repositories.IOrderDetailRepository;
import com.orange.repositories.IOrderRepository;
import com.orange.repositories.IProductDetailRepository;
import com.orange.repositories.IProductRepository;
import com.orange.services.IOrderService;
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
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderMapper  orderMapper;
    private final IOrderViewMapper orderViewMapper;
    private final IProductRepository productRepository;
    private final IProductDetailRepository productDetailRepository;


    @Override
    public OrderDTO create(OrderDTO dto) {

        dto.setOrderStatus(new OrderStatus(EOrderStatus.WAIT_FOR_CONFIRMATION.getId()));

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
    public Page<?> fillAll(Pageable pageable){
        org.springframework.data.domain.Page<Order> result = this.orderRepository.findAll(pageable);
        List<OrderViewDTO> viewDTOList = result.getContent()
                .stream()
                .map(orderViewMapper::toDto)
                .collect(Collectors.toList());
        int totalPages = result.getTotalPages();
        return Page.of(totalPages, result.getNumber(),totalPages, Math.toIntExact(result.getTotalElements()), viewDTOList);
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = getOrderById(id);
        return orderMapper.toDto(order);
    }

    private Order getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy order!"));
    }

    @Transactional
    @Override
    public OrderDTO updateOrderStatus(UpdateOrderStatus orderStatus) {
        if (orderStatus.getOrderId() == null) {
            throw new IllegalArgumentException("Không được để trống id");
        }
        if (orderStatus.getOrderStatusId() == null) {
            throw new IllegalArgumentException("Không được để trống trạng thái");
        }

        try {
            Order order = this.getOrderById(orderStatus.getOrderId());
            order.setOrderStatus(new OrderStatus(orderStatus.getOrderStatusId()));
            return orderMapper.toDto(this.orderRepository.save(order));
        } catch (DataAccessException e) {
            throw new OrderUpdateException("Có lỗi xảy ra trong quá trình cập nhật trạng thái");
        }
    }
}
