package com.example.quanlysieuthi.service.invoice;

import com.example.quanlysieuthi.dto.InvoiceDetailDTO;
import com.example.quanlysieuthi.entity.Customer;
import com.example.quanlysieuthi.entity.Invoice;
import com.example.quanlysieuthi.entity.Product;
import com.example.quanlysieuthi.exception.NotFoundException;
import com.example.quanlysieuthi.repository.CustomerRepository;
import com.example.quanlysieuthi.repository.InvoiceRepository;
import com.example.quanlysieuthi.repository.ProductRepository;
import com.example.quanlysieuthi.request.InvoiceDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService{
    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public InvoiceDetailDTO createInvoice(InvoiceDetailRequest invoiceDetailRequest) {
        InvoiceDetailDTO invoiceDetailDTO = new InvoiceDetailDTO();
        Invoice invoice = new Invoice();
        invoice.setDescriptionInvoice(invoiceDetailRequest.getDescriptionInvoice());
        Optional<Customer> customerSearch = customerRepository.findByPhoneNumber(invoiceDetailRequest.getCustomer().getPhoneNumber());
        if(customerSearch.isPresent()){
            invoiceDetailDTO.setCustomer(customerSearch.get());
        }else{
            invoiceDetailDTO.setCustomer(invoiceDetailRequest.getCustomer());
            customerRepository.save(invoiceDetailRequest.getCustomer());
        }
        invoice.setExportDate(LocalDateTime.now().toString());
        invoice.setProducts(invoiceDetailRequest.getProductList());
        List<Product> productList = invoiceDetailRequest.getProductList();
        Long sum = 0L;
        for(Product p: productList){
            Optional<Product> product = productRepository.findById(p.getIdProduct());
            if(product.isEmpty()){
                throw new NotFoundException("Không tồn tại sản phẩm", 500);
            }
            sum = sum + product.get().getProductPrice()* product.get().getQuantity();
        }
        invoice.setAmount(sum);
        invoice.setCustomer(customerSearch.get());
        invoiceRepository.save(invoice);
        invoiceDetailDTO.setInvoice(invoice);
        return invoiceDetailDTO;
    }

}
