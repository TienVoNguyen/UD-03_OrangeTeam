package com.orange.services.impl;

import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.model.Order;
import com.orange.domain.model.OrderDetail;
import com.orange.exception.EntityAlreadyExist;
import com.orange.exception.EntityNotFoundException;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.dto.OrderDTO;
import com.orange.exception.OrderUpdateException;
import com.orange.repositories.IOrderDetailRepository;
import com.orange.repositories.IOrderRepository;
import com.orange.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderMapper  orderMapper;
    private final IOrderViewMapper orderViewMapper;

    @Override
    public OrderDTO update(OrderDTO dto) {
        return null;
    }

    @Override
    public OrderDTO delete(OrderDTO dto) {
        return null;
    }

    @Override
    public OrderDTO create(OrderDTO dto) {
        if (dto.getId() == 0 || dto.getId() == null) {
            throw new EntityAlreadyExist("Order id must be null!");
        }
        Order result = this.orderRepository.save(orderMapper.toEntity(dto));
        Set<OrderDetail> orderDetails = result.getOrderDetails();
        orderDetails.forEach(o -> o.setOrder(result));
        orderDetailRepository.saveAll(orderDetails);
        result.setOrderDetails(orderDetails);
        return orderMapper.toDto(result);
    }

    @Override
    public Page<?> fillAll(Pageable pageable){
        Page<Order> result = this.orderRepository.findAll(pageable);
        List<OrderViewDTO> viewDTOList = result.getContent().stream()
                                                .map(orderViewMapper::toDto)
                                                .collect(Collectors.toList());
        return new PageImpl<>(viewDTOList, pageable, result.getTotalPages());
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = getOrderById(id);
        return orderMapper.toDto(order);
    }

    private Order getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    @Transactional
    public OrderDTO updateOrderStatus(OrderDTO orderDTO) {
        if (orderDTO.getId() == null) {
            throw new IllegalArgumentException("Order id must not be null");
        }
        if (orderDTO.getOrderStatus() == null) {
            throw new IllegalArgumentException("Order status must not be null");
        }

        try {
            Order order = this.getOrderById(orderDTO.getId());
            order.setOrderStatus(orderDTO.getOrderStatus());
            return orderMapper.toDto(this.orderRepository.save(order));
        } catch (DataAccessException e) {
            throw new OrderUpdateException("Error updating order status");
        }
    }
}
