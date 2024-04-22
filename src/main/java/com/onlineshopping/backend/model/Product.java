package com.onlineshopping.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Product name must contain atleast 3 characters")
    private String productName;

    @NotBlank
    @Size(min = 6, message = "Product description must contain atleast 6 characters")
    private String description;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

    @Column(name="image_url")
    private String imageUrl;

    private boolean active;

    @Column(name="units_in_stock")
    private int unitsInStock;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    private Date dateUpdated;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

}
