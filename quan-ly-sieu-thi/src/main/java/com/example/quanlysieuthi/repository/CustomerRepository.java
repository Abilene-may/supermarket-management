package com.example.quanlysieuthi.repository;

import com.example.quanlysieuthi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer WHERE phone_number = :phoneNumber", nativeQuery = true)
    Optional<Customer> findByPhoneNumber(String phoneNumber);

}
