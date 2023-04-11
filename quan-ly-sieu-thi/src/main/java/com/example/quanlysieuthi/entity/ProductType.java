package com.example.quanlysieuthi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "product_type")
@Table(name = "product_type")
public class ProductType {

  @Id
  @GeneratedValue(generator = "product_type_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "product_type_seq", sequenceName = "id_product_type", allocationSize = 1)
  private Long idProductType;
  @Column(name = "name_product_type")
  private String nameProductType;

  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "product_type_manufacturers",
      joinColumns = @JoinColumn(name = "product_type_id_product_type"),
      inverseJoinColumns = @JoinColumn(name = "manufacturers_id_manufacturer"))
  private Set<Manufacturer> manufacturers = new LinkedHashSet<>();

  public Set<Manufacturer> getManufacturers() {
    return manufacturers;
  }

  public void setManufacturers(Set<Manufacturer> manufacturers) {
    this.manufacturers = manufacturers;
  }
}
