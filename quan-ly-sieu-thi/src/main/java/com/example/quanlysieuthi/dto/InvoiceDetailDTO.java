package com.example.quanlysieuthi.dto;


import com.example.quanlysieuthi.entity.Customer;
import com.example.quanlysieuthi.entity.Invoice;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceDetailDTO {
    private Customer customer;
    private Invoice invoice;


}
