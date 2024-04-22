package com.onlineshopping.backend.response.dto;


import com.onlineshopping.backend.model.Address;
import com.onlineshopping.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private String orderStatus;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private Date dateCreated;
    private Address shippingAddress;
    private Address billingAddress;
    private User user;


}
