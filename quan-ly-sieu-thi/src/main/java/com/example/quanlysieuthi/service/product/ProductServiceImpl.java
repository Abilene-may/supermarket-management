package com.example.quanlysieuthi.service.product;

import com.example.quanlysieuthi.dto.ProductDTO;
import com.example.quanlysieuthi.dto.ProductDescriptionDTO;
import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.entity.ProductDescription;
import com.example.quanlysieuthi.exception.NotFoundException;
import com.example.quanlysieuthi.repository.ManufacturerRepository;
import com.example.quanlysieuthi.repository.ProductDescriptionRepository;
import com.example.quanlysieuthi.repository.ProductRepository;
import com.example.quanlysieuthi.request.ProductAndDesciptionRequest;
import com.example.quanlysieuthi.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductDescriptionRepository productDescriptionRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByID(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy id = " + id, 500);
        }
        return product.get();
    }

    @Override
    public ProductDTO getProductByName(ProductRequest productRequest) {
        List<Product> product = productRepository.findByNameProduct(productRequest.getProductName());
        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm " + productRequest.getProductName() + " hoặc đã bị xóa", 500);
        }
        ProductDTO productDTOS = new ProductDTO();
        productDTOS.setProductList(product);
        return productDTOS;
    }

    @Override
    public ProductDTO getProductByPrice(ProductRequest productRequest) {
        if (productRequest.getMinProductPrice() == null) {
            productRequest.setMinProductPrice(0L);
        }
        if (productRequest.getMaxProductPrice() == null) {
            Long maxPrice = productRepository.findMaxProductPrice();
            productRequest.setMaxProductPrice(maxPrice);
        }
        List<Product> productList = productRepository.findByProductPrice(productRequest.getMinProductPrice(), productRequest.getMaxProductPrice());
        if (productList.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm nào", 500);
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductList(productList);
        return productDTO;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(productRequest.getIdManufacturer());
        if (manufacturer.isEmpty()) {
            throw new NotFoundException("Không tìm thấy hãng sản xuất", 500);
        }
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setManufacturer(manufacturer.get());
        productRepository.save(product);
        return product;
    }


    @Transactional
    @Override
    public ProductDescriptionDTO createProductAndDescription(ProductAndDesciptionRequest productAndDesciptionRequest) {
        ProductDescriptionDTO descriptionDTO = new ProductDescriptionDTO();
        Product product = new Product();
        product.setProductName(productAndDesciptionRequest.getProductName());
        product.setProductPrice(productAndDesciptionRequest.getProductPrice());
        product.setQuantity(productAndDesciptionRequest.getQuantity());
        descriptionDTO.setProduct(product);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setDescription(productAndDesciptionRequest.getDescription());
        productDescription.setDiscount(productAndDesciptionRequest.getDiscount());
        productDescription.setImageUrl(productAndDesciptionRequest.getImageUrl());
        descriptionDTO.setProductDescription(productDescription);
        productDescriptionRepository.save(productDescription);
        product.setProductDescription(productDescription);
        productRepository.save(product);
        return descriptionDTO;
    }

    @Override
    public Product updateProduct(ProductRequest productRequest) {
        Optional<Product> prodcutSearch = productRepository.findById(productRequest.getIdProduct());
        if (prodcutSearch.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm với id = " + productRequest.getIdProduct(), 500);
        }
        Product product = prodcutSearch.get();
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setQuantity(prodcutSearch.get().getQuantity() + productRequest.getQuantity());
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productSearch = productRepository.findById(id);
        if (productSearch.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm id = " + id, 500);
        }
        productRepository.delete(productSearch.get());
    }
}
