package com.example.quanlysieuthi.service.manufacturer;

import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.exception.NotFoundException;
import com.example.quanlysieuthi.repository.ManufacturerRepository;
import com.example.quanlysieuthi.request.ManufacturerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerByName(String nameManufacturer) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findByNameManufacturer(nameManufacturer);
        if(manufacturer.isEmpty()){
            throw new NotFoundException("Không tìm thấy hãng sản xuất id là " + nameManufacturer, 500);
        }
        return manufacturer.get();
    }

    @Override
    public Manufacturer createManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setNameManufacturer(manufacturerRequest.getNameManufacturer());
        manufacturer.setOrigin(manufacturerRequest.getOrigin());
        manufacturer.setFoundedYear(manufacturerRequest.getFoundedYear());
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    @Override
    public Manufacturer updateManufacturer(ManufacturerRequest manufacturerRequest) {
        Optional<Manufacturer> manufacturerSearch = manufacturerRepository.findById(manufacturerRequest.getIdManufacturer());
        if (manufacturerSearch.isEmpty()){
            throw  new NotFoundException("Không tìm thấy hãng sản xuất id " + manufacturerRequest.getIdManufacturer(), 500);
        }
        Manufacturer manufacturer = manufacturerSearch.get();
        manufacturer.setNameManufacturer(manufacturerRequest.getNameManufacturer());
        manufacturer.setOrigin(manufacturerRequest.getOrigin());
        manufacturer.setFoundedYear(manufacturerRequest.getFoundedYear());
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    @Override
    public void updateManufacturerByID(ManufacturerRequest manufacturerRequest) {
       try{
           manufacturerRepository.updateMaufacturer(manufacturerRequest.getIdLoaiSanPham(), manufacturerRequest.getNameManufacturer(), manufacturerRequest.getOrigin(), manufacturerRequest.getFoundedYear());

       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public void deleteManufacturer(String nameManufacturer) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findByNameManufacturer(nameManufacturer);
        if(manufacturer.isEmpty()){
            throw new NotFoundException("Không tìm thấy hãng sản xuất: " + nameManufacturer, 500);
        }
        manufacturerRepository.delete(manufacturer.get());
    }

}
