package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.request.ManufacturerRequest;
import com.example.quanlysieuthi.service.manufacturer.ManufacturerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {

  private final ManufacturerService manufacturerService;

  @GetMapping("/get-all-manufacturer")
  public ResponseEntity<List<Manufacturer>> getAllManufacturer() {
    return new ResponseEntity<>(manufacturerService.getAllManufacturer(), HttpStatus.OK);
  }

  @PostMapping("/get-manufacturer-by-name")
  public ResponseEntity<Manufacturer> getManufacturerByName(
      @RequestBody ManufacturerRequest manufacturerRequest) {
    return new ResponseEntity<>(
        manufacturerService.getManufacturerByName(manufacturerRequest.getNameManufacturer()),
        HttpStatus.OK);
  }

  @PutMapping("/create-manufacturer")
  public ResponseEntity<Manufacturer> createManufacturer(
      @RequestBody ManufacturerRequest manufacturerRequest) {
    return new ResponseEntity<>(manufacturerService.createManufacturer(manufacturerRequest),
        HttpStatus.OK);
  }

  @PutMapping("/update-manufacturer")
  public ResponseEntity<String> updateManufacturer(
      @RequestBody ManufacturerRequest manufacturerRequest) {
    manufacturerService.updateManufacturerByID(manufacturerRequest);
    return new ResponseEntity<>("Đã cập nhật thành công ", HttpStatus.OK);
  }

  @DeleteMapping("/delete-manufacturer")
  public ResponseEntity<String> deleteManufacturer(@RequestParam String nameManufacturer) {
    manufacturerService.deleteManufacturer(nameManufacturer);
    return new ResponseEntity<>("Xóa thành công hãng sản xuất \"" + nameManufacturer + "\"",
        HttpStatus.OK);
  }

}
