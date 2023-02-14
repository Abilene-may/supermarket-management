package com.example.quanlysieuthi.request;

import com.example.quanlysieuthi.entity.Customer;
import com.example.quanlysieuthi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InvoiceDetailRequest {
    private String descriptionInvoice;
    private Customer customer;
    private List<Product> productList;
}
