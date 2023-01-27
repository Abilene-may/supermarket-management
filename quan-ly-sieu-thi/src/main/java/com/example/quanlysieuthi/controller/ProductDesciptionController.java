package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.dto.ProductDescriptionDTO;
import com.example.quanlysieuthi.service.file.ReadImageService;
import com.example.quanlysieuthi.service.productdescription.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-description")
public class ProductDesciptionController {
    @Autowired
    private ReadImageService readImageService;
    @Autowired
    private ProductDescriptionService descriptionService;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImg(){
        byte[] bytes = readImageService.readFile("banh_oishi.png");
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @GetMapping("/information-product")
    public ResponseEntity<ProductDescriptionDTO> getInformation(@RequestParam Long id){
        ProductDescriptionDTO productDescriptionDTO = descriptionService.getInformationProduct(id);
        return new ResponseEntity<>(productDescriptionDTO, HttpStatus.OK);
    }




}
