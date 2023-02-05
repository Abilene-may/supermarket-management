package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column (name = "export_date")
    private Date exportDate;

    @ManyToOne
    @JoinColumn(name = "customer_id_customer")
    @JsonIgnore
    private Customer customer;

}
