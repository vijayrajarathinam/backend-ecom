package com.onlineshopping.backend.service;

import com.onlineshopping.backend.response.OrderResponse;
import com.onlineshopping.backend.response.dto.CartDTO;
import com.onlineshopping.backend.response.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO placeOrder(String emailId, CartDTO cart, String paymentMethod);

    OrderDTO getOrder(String emailId, Long orderId);

    List<OrderDTO> getOrdersByUser(String emailId);

    OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}
