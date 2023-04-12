package com.example.quanlysieuthi.repository;

import com.example.quanlysieuthi.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaSpecificationExecutor<Product>,
    JpaRepository<Product, Long> {
  Optional<Product> findByIdProduct(Long idProduct);
  @Query(value = "SELECT * FROM product p WHERE p.name_product = :name",
      nativeQuery = true)
  List<Product> findByNameProduct(String name);

  @Query(value = "SELECT * FROM product\n" 
         + "WHERE product_price BETWEEN :minProductPrice AND :maxProductPrice"
      ,nativeQuery = true)
  List<Product> findByProductPrice(Long minProductPrice, Long maxProductPrice);

  @Query(value = "SELECT MAX(product_price) FROM product",
      nativeQuery = true)
  Long findMaxProductPrice();

  @Query(value = "SELECT p.name_product, pt.name_product_type, p.product_price " +
      "FROM product p JOIN product_type pt on p.product_type_id_product_type  = pt.id_product_type "
      +
      "WHERE " +
      " ( case when :nameProduct is not null and :nameProductType is not null then 0" +
      " when :nameProduct is null and :nameProductType is not null then 1 " +
      " when :nameProduct is not null and :nameProductType is null then 2 " +
      " when :nameProduct is null and :nameProductType is null then 3 " +
      "end ) = " +
      " ( case when :nameProduct is not null and :nameProductType is not null" +
      " and p.name_product = :nameProduct and pt.name_product_type = :nameProductType then 0" +
      " when :nameProduct is null and :nameProductType is not null " +
      " and pt.name_product_type = :nameProductType then 1 " +
      " when :nameProduct is not null and :nameProductType is null and " +
      " p.name_product = :nameProduct then 2 " +
      " when :nameProduct is null and :nameProductType is null and 1=1 then 3 end )"
      , nativeQuery = true)
  List<Object[]> findByNameProductOrNameProductType(String nameProduct, String nameProductType);
}
