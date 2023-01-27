package com.example.quanlysieuthi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDesciptionRequest {
    private Long idProductDescription;
    private float discount;
}
