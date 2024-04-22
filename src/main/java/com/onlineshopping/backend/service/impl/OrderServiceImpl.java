package com.onlineshopping.backend.service.impl;

import com.onlineshopping.backend.constants.OrderStatus;
import com.onlineshopping.backend.exception.OrderNotFoundException;
import com.onlineshopping.backend.exception.ProductNotFoundException;
import com.onlineshopping.backend.model.Order;
import com.onlineshopping.backend.model.OrderItem;
import com.onlineshopping.backend.repository.OrderRepository;
import com.onlineshopping.backend.repository.UserRepository;
import com.onlineshopping.backend.response.OrderResponse;
import com.onlineshopping.backend.response.dto.CartDTO;
import com.onlineshopping.backend.response.dto.OrderDTO;
import com.onlineshopping.backend.response.dto.OrderItemDTO;
import com.onlineshopping.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO placeOrder(String emailId, CartDTO cart, String paymentMethod) {

        if(cart == null || cart.getCartItems().size() <= 0)
            throw new ProductNotFoundException("Please add some products to the cart");

        Order order = new Order();
        order.setUser(userRepository.findByEmail(emailId).get());
        order.setOrderStatus(String.valueOf(OrderStatus.ACCEPTED));

        List<OrderItem> orderItems = new ArrayList<>();

        cart.getCartItems().forEach(cartItem -> {

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getProductPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItems.add(orderItem);

        });

        order.setOrderItems(orderItems);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        orderItems.forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDTO.class)));

        return orderDTO;
    }

    @Override
    public OrderDTO getOrder(String emailId, Long orderId) {
        Order order = orderRepository.findOrderByEmailAndOrderId(emailId, orderId);
        if (order == null)
            throw new OrderNotFoundException("Order with  orderId"+ orderId+ " not found");

        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(String emailId) {
        return null;
    }

    @Override
    public OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Order> pageOrders = orderRepository.findAll(pageDetails);

        List<Order> orders = pageOrders.getContent();

        List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());

        if (orderDTOs.size() == 0) {
            throw new OrderNotFoundException("No orders placed yet by the users");
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setContent(orderDTOs);
        orderResponse.setPageNumber(pageOrders.getNumber());
        orderResponse.setPageSize(pageOrders.getSize());
        orderResponse.setTotalElements(pageOrders.getTotalElements());
        orderResponse.setTotalPages(pageOrders.getTotalPages());
        orderResponse.setLastPage(pageOrders.isLast());

        return orderResponse;
    }

    @Override
    public OrderDTO updateOrder(String emailId, Long orderId, String orderStatus) {
        Order order = orderRepository.findOrderByEmailAndOrderId(emailId, orderId);
        if (order == null)
            throw new OrderNotFoundException("Order with  orderId"+ orderId+ " not found");

        order.setOrderStatus(orderStatus);
        return modelMapper.map(order, OrderDTO.class);
    }
}
