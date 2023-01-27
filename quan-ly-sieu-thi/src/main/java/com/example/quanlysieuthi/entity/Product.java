package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "product")
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue (generator = "product_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq_id", allocationSize = 1)
    private Long idProduct;
    @Column (name = "name_product")
    private String productName;
    @Column (name = "product_price")
    private Long productPrice;
    @Column (name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn (name = "manufacturer_id")
    @JsonIgnore
    private Manufacturer manufacturer;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "product_description_id_product_description")
    @JsonIgnore
    private ProductDescription productDescription;

}
