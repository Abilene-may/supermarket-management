package com.example.quanlysieuthi.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductAndDesciptionRequest {
    private Long idProduct;
    private String productName;
    private Long productPrice;
    private int quantity;
    private Long idProductDescription;
    private String description;
    private float discount;
    private String imageUrl;

}
