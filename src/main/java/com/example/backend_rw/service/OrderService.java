package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllByUser(Integer userId);
}
