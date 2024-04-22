package com.onlineshopping.backend.response.dto;

import com.onlineshopping.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private User user;
    private List<CartItemDTO> cartItems = new ArrayList<>();
    private Double totalPrice = 0.0;

}
