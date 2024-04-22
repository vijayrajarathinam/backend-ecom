package com.onlineshopping.backend.response.dto;

import com.onlineshopping.backend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Product product;
    private Integer quantity;
    private BigDecimal productPrice;
}
