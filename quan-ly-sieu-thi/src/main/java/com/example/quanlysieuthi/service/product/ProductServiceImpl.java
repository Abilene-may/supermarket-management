package com.example.quanlysieuthi.service.product;

import com.example.quanlysieuthi.dto.ProductAndProductTypeDTO;
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
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
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
      throw new NotFoundException(
          "Không tìm thấy sản phẩm " + productRequest.getProductName() + " hoặc đã bị xóa", 500);
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
    List<Product> productList = productRepository.findByProductPrice(
        productRequest.getMinProductPrice(), productRequest.getMaxProductPrice());
    if (productList.isEmpty()) {
      throw new NotFoundException("Không tìm thấy sản phẩm nào", 500);
    }
    ProductDTO productDTO = new ProductDTO();
    productDTO.setProductList(productList);
    return productDTO;
  }

  @Override
  public Specification<Product> getListProductByName(String productName) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (StringUtils.isNotBlank(productName)) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("productName")), "%" + productName + "%"));
      }
      return query.where(predicates.toArray(new Predicate[0])).getRestriction();
    };
  }

  @Override
  public List<ProductAndProductTypeDTO> getListProductByNameProduct(String nameProduct,
      String nameProductType) {
    List<Object[]> objects = productRepository.findByNameProductOrNameProductType(nameProduct,
        nameProductType);
    if (objects.isEmpty()) {
      log.info("Không tìm thấy thông tin bạn muốn tìm");
      throw new NotFoundException("Không tìm thấy thông tin bạn muốn tìm", 500);
    }
    List<ProductAndProductTypeDTO> typeDTOList = new ArrayList<>();
    for (Object[] o : objects) {
      ProductAndProductTypeDTO productTypeDTO = new ProductAndProductTypeDTO();
      productTypeDTO.setProductName((String) o[0]);
      productTypeDTO.setProductTypeName((String) o[1]);
      productTypeDTO.setProductPrice((Long) o[2]);
      typeDTOList.add(productTypeDTO);
    }
    return typeDTOList;
  }

  @Override
  public List<Product> getListProductByNameProductAndProductPrice(String nameProduct,
      Long productPrice) {
    Specification<Product> specification =
        (root, query, criteriaBuilder) -> {
          List<Predicate> predicates = new ArrayList<>();
          if (StringUtils.isNotBlank(nameProduct)) {
            predicates.add(
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("productName")), "%" + nameProduct + "%"));
          }
          if (productPrice != null) {
            predicates.add(
                criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), productPrice)
            );
          }
          return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    return productRepository.findAll(specification);
  }

  @Override
  public Product createProduct(ProductRequest productRequest) {
    Optional<Manufacturer> manufacturer = manufacturerRepository.findById(
        productRequest.getIdManufacturer());
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
  public ProductDescriptionDTO createProductAndDescription(
      ProductAndDesciptionRequest productAndDesciptionRequest) {
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
      throw new NotFoundException(
          "Không tìm thấy sản phẩm với id = " + productRequest.getIdProduct(), 500);
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
