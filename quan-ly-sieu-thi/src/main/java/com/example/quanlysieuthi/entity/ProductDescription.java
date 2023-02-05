package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "product_description")
@Table(name = "product_description")
public class ProductDescription {
    @Id
    @GeneratedValue(generator = "product_description_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_description_seq", sequenceName = "id_product_description_seq", allocationSize = 1)
    private Long idProductDescription;

    @Column(name = "description")
    private String description;
    @Column(name = "discount")
    private float discount;
    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(mappedBy = "productDescription", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Product product;

}
