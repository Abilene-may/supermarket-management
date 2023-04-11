package com.example.quanlysieuthi.service.producttype;

import com.example.quanlysieuthi.entity.Manufacturer;
import com.example.quanlysieuthi.entity.ProductType;
import com.example.quanlysieuthi.exception.NotFoundException;
import com.example.quanlysieuthi.repository.ManufacturerRepository;
import com.example.quanlysieuthi.repository.ProductTypeRepository;
import com.example.quanlysieuthi.request.ProductTypeRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

  @Autowired
  private ProductTypeRepository productTypeRepository;
  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Override
  public List<ProductType> getAllProductType() {
    return productTypeRepository.findAll();
  }

  @Override
  public ProductType updateProductType(ProductTypeRequest productTypeRequest) {
    Optional<ProductType> productTypeSearch = productTypeRepository.findByNameProductType(
        productTypeRequest.getNameProductType());
    if (productTypeSearch.isEmpty()) {
      throw new NotFoundException(
          "Không tìm thấy loại sản phẩm: " + productTypeRequest.getNameProductType(), 500);
    }
    ProductType productType = new ProductType();
    productType.setNameProductType(productTypeRequest.getNameProductType());
    productTypeRepository.save(productType);
    return productType;
  }

  @Override
  public ProductType createProductType(ProductTypeRequest productTypeRequest) {
    ProductType productType = new ProductType();
    productType.setNameProductType(productTypeRequest.getNameProductType());
    productTypeRepository.save(productType);
    return productType;
  }


  @Override
  public List<Manufacturer> getListManufacturerByNameProductType(String nameProductType) {
    List<Manufacturer> manufacturers = manufacturerRepository.findListManufacturerByNameProductType(
        nameProductType);
    if (manufacturers.isEmpty()) {
      throw new NotFoundException("Không tìm thấy dữ liệu tên loại sản phẩm: " + nameProductType,
          500);
    }
    return manufacturers;
  }

  @Override
  public void deleteProductType(String nameProductType) {
    Optional<ProductType> prodcutType = productTypeRepository.findByNameProductType(
        nameProductType);
    if (prodcutType.isEmpty()) {
      throw new NotFoundException("Không tìm thấy loại sản phẩm: \"" + nameProductType + "\"", 500);
    }
    productTypeRepository.delete(prodcutType.get());
  }


}
