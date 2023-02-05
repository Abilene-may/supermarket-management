package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "customer")
@Table (name = "customer")
public class Customer {

    @Id
    @GeneratedValue(generator = "customer_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq_id", allocationSize = 1)
    private Long idCustomer;
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private Set<Invoice> invoices = new LinkedHashSet<>();

}
