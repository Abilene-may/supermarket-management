package com.example.quanlysieuthi.dto;

import com.example.quanlysieuthi.entity.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductTypeDTO {
    private String nameProductType;
    private List<Manufacturer> manufacturers;
    private int numOfManufacturer;    // số lượng hãng sãn xuất theo loại sản phẩm


}
