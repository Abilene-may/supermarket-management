package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.dto.InvoiceDetailDTO;
import com.example.quanlysieuthi.request.InvoiceDetailRequest;
import com.example.quanlysieuthi.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/create-invoice")
    public ResponseEntity<InvoiceDetailDTO> createInvoice(@RequestBody InvoiceDetailRequest invoiceDetailRequest){
        InvoiceDetailDTO invoiceDetailDTO = invoiceService.createInvoice(invoiceDetailRequest);
        return new ResponseEntity<>(invoiceDetailDTO, HttpStatus.OK);
    }
}
