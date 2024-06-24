package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Order;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.order.OrderResponse;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderResponse> getAllByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User ID không tồn tại"));

        List<Order> listOrders = orderRepository.findAllByUser(user);
        return listOrders.stream().map(this::convertToOrderResponse).toList();
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        orderResponse.setCourseName(order.getCourses().getTitle());
        orderResponse.setCustomerName(order.getUser().getFullName());
        return orderResponse;
    }
}
