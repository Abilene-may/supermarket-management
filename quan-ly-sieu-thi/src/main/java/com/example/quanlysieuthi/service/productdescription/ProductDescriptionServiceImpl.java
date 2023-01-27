package com.example.quanlysieuthi.service.productdescription;

import com.example.quanlysieuthi.dto.ProductDescriptionDTO;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.entity.ProductDescription;
import com.example.quanlysieuthi.exception.NotFoundException;
import com.example.quanlysieuthi.repository.ProductDescriptionRepository;
import com.example.quanlysieuthi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDescriptionRepository productDescriptionRepository;

    @Override
    public ProductDescriptionDTO getInformationProduct(Long idProduct) {
        Optional<Product> productSearch = productRepository.findById(idProduct);
        if(productSearch.isEmpty()){
            throw new NotFoundException("Không tìm thấy sản phẩm id " + idProduct, 500);
        }
        Optional<ProductDescription> productDescription = productDescriptionRepository.findById(productSearch.get().getProductDescription().getIdProductDescription());
        ProductDescriptionDTO descriptionDTO = new ProductDescriptionDTO();
        descriptionDTO.setProduct(productSearch.get());
        descriptionDTO.setProductDescription(productDescription.get());
        return descriptionDTO;
    }



}
