package com.example.quanlysieuthi.service.product;

import com.example.quanlysieuthi.dto.ProductDTO;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProductByID(Long id);
    ProductDTO getProductByName(ProductRequest productRequest);
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);

}
