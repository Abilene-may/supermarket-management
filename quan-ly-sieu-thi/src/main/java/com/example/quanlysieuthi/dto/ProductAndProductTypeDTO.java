package com.example.quanlysieuthi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductAndProductTypeDTO {
    private String productName;
    private String productTypeName;
    private Long productPrice;
}
