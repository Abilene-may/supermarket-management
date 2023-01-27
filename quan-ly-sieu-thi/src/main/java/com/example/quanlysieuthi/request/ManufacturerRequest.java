package com.example.quanlysieuthi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ManufacturerRequest {
    private Long idManufacturer;
    private String nameManufacturer;
    private String origin;
    private int foundedYear;
    private Long idLoaiSanPham;
}
