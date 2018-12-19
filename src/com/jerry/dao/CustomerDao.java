package com.jerry.dao;

import com.jerry.domain.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    void save(Customer c);

    Customer findById(String customerId);

    void update(Customer c);

    void delete(String customerId);
}
