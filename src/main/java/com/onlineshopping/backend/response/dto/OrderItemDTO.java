package com.onlineshopping.backend.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal unitPrice;
    private double orderedProductPrice;
}