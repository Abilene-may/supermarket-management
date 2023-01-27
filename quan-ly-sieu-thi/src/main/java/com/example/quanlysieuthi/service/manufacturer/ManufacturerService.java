package com.example.quanlysieuthi.service.manufacturer;

import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.request.ManufacturerRequest;

import java.util.List;
public interface ManufacturerService {
    List<Manufacturer> getAllManufacturer();
    Manufacturer getManufacturerByName(String nameManufacturer);
    Manufacturer createManufacturer(ManufacturerRequest manufacturerRequestRequest);
    Manufacturer updateManufacturer(ManufacturerRequest manufacturerRequest);
    void updateManufacturerByID(ManufacturerRequest manufacturerRequest);
    void deleteManufacturer(String nameManufacturer);

}
