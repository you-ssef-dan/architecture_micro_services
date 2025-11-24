package com.example.demo.web;

import com.example.demo.entities.Bill;
import com.example.demo.feign.CustomerRestClient;
import com.example.demo.feign.ProductRestClient;
import com.example.demo.repositories.BillRepository;
import com.example.demo.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));

        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });

        return bill;
    }

    @GetMapping("/bills/customer/{customerId}")
    public List<Bill> getBillsByCustomer(@PathVariable Long customerId){
        List<Bill> bills = billRepository.findByCustomerId(customerId);
        bills.forEach(bill -> {
            bill.setCustomer(customerRestClient.getCustomerById(customerId));

            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(
                        productRestClient.getProductById(productItem.getProductId())
                );
            });
        });
        return bills;

    }

}
