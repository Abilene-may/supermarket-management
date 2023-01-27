package com.example.quanlysieuthi.repository;

import com.example.quanlysieuthi.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @Query(value = "SELECT hsx.id_manufacturer , hsx.name_manufacturer , hsx.origin , hsx.founded_year\n" +
            "FROM manufacturer hsx LEFT JOIN  product_type_manufacturers lsphsx on hsx.id_manufacturer  = lsphsx.manufacturers_id_manufacturer  \n" +
            " LEFT JOIN product_type lsp on lsp.id_product_type = lsphsx.product_type_id_product_type  \n" +
            "WHERE lsp.name_product_type = :nameProductType", nativeQuery = true)
    List<Manufacturer> findListManufacturerByNameProductType(String nameProductType);

    @Modifying
//    @Transactional
    @Query(value = "UPDATE manufacturer \n" +
            "SET name_manufacturer = :nameManufacturer, origin = :origin, founded_year = :foundedYear \n" +
            "WHERE id_manufacturer = :idManufacturer ", nativeQuery = true)
    void updateMaufacturer(Long idManufacturer, String nameManufacturer,String origin ,int foundedYear);
    Optional<Manufacturer> findByNameManufacturer(String nameManufacturer);

}
