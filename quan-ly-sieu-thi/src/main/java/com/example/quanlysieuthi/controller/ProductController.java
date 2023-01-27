package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.dto.ProductDTO;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.request.ProductRequest;
import com.example.quanlysieuthi.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public ResponseEntity<List<Product>> getAllProdcut(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping("/get-product-by-id")
    public ResponseEntity<Product> getProductByID(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.getProductByID(productRequest.getIdProduct()), HttpStatus.OK);
    }

    @PostMapping("/get-product-by-name")
    public ResponseEntity<ProductDTO> getAllProductByName(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.getProductByName(productRequest), HttpStatus.OK);
    }

    @PutMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.OK);
    }

    @PutMapping ("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.updateProduct(productRequest), HttpStatus.OK);
    }

    @DeleteMapping ("/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Đã xóa thành công id " + id, HttpStatus.OK);
    }

}
