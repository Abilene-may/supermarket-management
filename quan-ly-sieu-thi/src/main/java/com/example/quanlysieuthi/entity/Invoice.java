package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "invoice")
@Table (name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(generator = "invoice_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "invoice_seq", sequenceName = "invoice_seq_id", allocationSize = 1)
    private Long idInvoice;
    private String descriptionInvoice;
    @Column (name = "export_date")
    private String exportDate;
    private Long amount;
    @ManyToMany
    @JoinTable(name = "invoice_products",
            joinColumns = @JoinColumn(name = "invoice_id_invoice"),
            inverseJoinColumns = @JoinColumn(name = "products_id_product"))
    @JsonIgnore
    private List<Product> products;
    @ManyToOne
    @JoinColumn (name = "manufacturer_id")
    @JsonIgnore
    private Customer customer;
}
