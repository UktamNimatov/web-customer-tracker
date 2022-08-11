package com.dao;

import com.entity.Customer;

import java.util.List;


public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(Customer customer);
}
