package com.example.quanlysieuthi.repository;

import com.example.quanlysieuthi.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Query(value = "SELECT * FROM product_type sp WHERE LOWER(sp.name_product_type) LIKE LOWER(CONCAT('%', :nameProductType, '%'))", nativeQuery = true)
    Optional<ProductType> findByNameProductType(String nameProductType);

}
