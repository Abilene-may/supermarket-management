package com.example.quanlysieuthi.dto;

import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.entity.ProductDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDescriptionDTO {
    private Product product;
    private ProductDescription productDescription;
}
