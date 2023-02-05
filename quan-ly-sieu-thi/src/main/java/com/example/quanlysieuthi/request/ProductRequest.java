package com.example.quanlysieuthi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private Long idProduct;
    private String productName;
    private Long productPrice;
    private int quantity;
    private Long idManufacturer;
    private Long minProductPrice;
    private Long maxProductPrice;
}
