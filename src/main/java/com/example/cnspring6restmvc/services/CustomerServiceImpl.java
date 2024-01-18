package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.fromString("efa4211e-8060-4941-aa52-95986debbf9b"))
                .customerName("DY")
                .version(1L)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.fromString("3b05b574-fb6e-4783-864e-dd956bc773d4"))
                .customerName("Feid")
                .version(1L)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);

    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        log.info("Get Customer by Id - In getCustomerById with id {}",id.toString());
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        log.info("Inside CustomerService - listCustomers() ..");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO updatedCustomer = customerMap.get(customerId);
        updatedCustomer.setCustomerName(customer.getCustomerName());
        updatedCustomer.setVersion(customer.getVersion());
        return Optional.of(updatedCustomer);
        //customerMap.put(updatedCustomer.getId(), updatedCustomer);

    }

    @Override
    public void deleteById(UUID customerId) {
        customerMap.remove(customerId);
    }
}
