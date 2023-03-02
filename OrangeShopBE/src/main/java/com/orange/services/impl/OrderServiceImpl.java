package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.model.Order;
import com.orange.domain.model.OrderDetail;
import com.orange.domain.model.ProductDetail;
import com.orange.exception.EntityNotFoundException;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.dto.OrderDTO;
import com.orange.exception.NotEnoughStockException;
import com.orange.repositories.IOrderDetailRepository;
import com.orange.repositories.IOrderRepository;
import com.orange.repositories.IProductDetailRepository;
import com.orange.repositories.IProductRepository;
import com.orange.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Order order = this.orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new EntityNotFoundException("Order not found!");
        }
        return orderMapper.toDto(order);
    }
}
