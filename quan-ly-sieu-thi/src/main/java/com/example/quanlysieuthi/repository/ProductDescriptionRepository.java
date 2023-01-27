package com.example.quanlysieuthi.repository;

import com.example.quanlysieuthi.entity.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {


}
