package com.example.quanlysieuthi.service.product;

import com.example.quanlysieuthi.dto.ProductAndProductTypeDTO;
import com.example.quanlysieuthi.dto.ProductDTO;
import com.example.quanlysieuthi.dto.ProductDescriptionDTO;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.request.ProductAndDesciptionRequest;
import com.example.quanlysieuthi.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProductByID(Long id);
    ProductDTO getProductByName(ProductRequest productRequest);
    ProductDTO getProductByPrice(ProductRequest productRequest);

    List<ProductAndProductTypeDTO> getListProductByNameProduct(String nameProduct, String nameProductType);
    List<Product> getListProductByNameProductAndNameProductType2(String nameProduct);
    Product createProduct(ProductRequest productRequest);
    ProductDescriptionDTO createProductAndDescription(ProductAndDesciptionRequest productAndDesciptionRequest);
    Product updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);

}
