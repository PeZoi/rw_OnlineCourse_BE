package com.example.backend_rw.service;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllByUser(Integer userId);

    void createOrder(User user, Courses courses, int totalPrice);

    List<OrderResponse> getAll();
}
