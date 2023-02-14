package com.example.quanlysieuthi.service.invoice;

import com.example.quanlysieuthi.dto.InvoiceDetailDTO;
import com.example.quanlysieuthi.request.InvoiceDetailRequest;


public interface InvoiceService {
    InvoiceDetailDTO createInvoice(InvoiceDetailRequest invoiceDetailRequest);
}
