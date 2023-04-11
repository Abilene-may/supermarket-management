package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "manufacturer")
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "manufacturer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "manufacturer_seq", sequenceName = "maufacturer_seq_id", allocationSize = 1)
    private Long idManufacturer;
    @Column (name = "name_manufacturer")
    private String nameManufacturer;
    @Column (name = "origin")
    private String origin;
    @Column (name = "founded_year")
    private int foundedYear;

    //@OneToMany một hãng sản xuất có nhiều sản phẩm
    @OneToMany (mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    @ManyToMany(mappedBy = "manufacturers", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductType> productTypes = new LinkedHashSet<>();
}
