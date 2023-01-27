package com.example.quanlysieuthi.service.producttype;
import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.entity.ProductType;
import com.example.quanlysieuthi.request.ProductTypeRequest;
import java.util.List;

public interface ProductTypeService {
    List<ProductType> getAllProductType();
    ProductType updateProductType(ProductTypeRequest productTypeRequest);
    ProductType createProductType(ProductTypeRequest productTypeRequest);
    List<Manufacturer> getListManufacturerByNameProductType(String nameProductType);
    void deleteProductType(String nameProductType);


}
