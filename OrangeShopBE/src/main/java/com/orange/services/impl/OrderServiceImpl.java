package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.model.Order;
import com.orange.domain.model.OrderDetail;
import com.orange.exception.EntityNotFoundException;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.dto.OrderDTO;
import com.orange.repositories.IOrderDetailRepository;
import com.orange.repositories.IOrderRepository;
import com.orange.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderMapper  orderMapper;
    private final IOrderViewMapper orderViewMapper;


    @Override
    public OrderDTO create(OrderDTO dto) {
        Order result = this.orderRepository.save(orderMapper.toEntity(dto));
        Set<OrderDetail> orderDetails = result.getOrderDetails();
        orderDetails.forEach(o -> o.setOrder(result));
        orderDetailRepository.saveAll(orderDetails);
        result.setOrderDetails(orderDetails);
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
        int totalPages = result.getTotalPages();
        List<Order> orderList = result.toList();
//        List<OrderDTO> orderDTOList = orderMapper.toDtoList(orderList);
//        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, totalPages);
        List<OrderViewDTO> viewDTOList = orderViewMapper.toDtoList(orderList);
        Page<OrderViewDTO> pageViewDTO = new Page<>();
        pageViewDTO.setTotalPages(totalPages);
        pageViewDTO.setPageNumber(result.getNumber());
        pageViewDTO.setResult(viewDTOList);
        pageViewDTO.setPageSize(result.getSize());
        pageViewDTO.setTotalItems((int) result.getTotalElements());


        return pageViewDTO;
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
