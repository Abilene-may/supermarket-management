package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.entity.ProductType;
import com.example.quanlysieuthi.request.ProductTypeRequest;
import com.example.quanlysieuthi.service.producttype.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/product-type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping ("/get-all-product-type")
    public ResponseEntity<List<ProductType>> getAllProductType(){
        return new ResponseEntity<>(productTypeService.getAllProductType(), HttpStatus.OK);
    }

    @PostMapping ("/get-list-product-type-by-name-product-type")
    public ResponseEntity<List<Manufacturer>> getListManufacturerByProdcutType(@RequestBody ProductTypeRequest productTypeRequest){
        return new ResponseEntity<>(productTypeService.getListManufacturerByNameProductType(productTypeRequest.getNameProductType()), HttpStatus.OK);
    }

    @PutMapping("/create-product-type")
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductTypeRequest productTypeRequest){
        return new ResponseEntity<>(productTypeService.createProductType(productTypeRequest), HttpStatus.OK);
    }

    @PutMapping ("/update-product-type")
    public ResponseEntity<ProductType> updateProductType(@RequestBody ProductTypeRequest productTypeRequest){
        return new ResponseEntity<>(productTypeService.updateProductType(productTypeRequest), HttpStatus.OK);
    }


    @DeleteMapping ("/delete-product-type")
    public ResponseEntity<String> deleteProductType(@RequestParam String nameProductType){
        productTypeService.deleteProductType(nameProductType);
        return new ResponseEntity<>("Xóa thành công loại sản phẩm: " + nameProductType, HttpStatus.OK);
    }

}
