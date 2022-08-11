package com.service;

import com.dao.CustomerDAO;
import com.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerDAO customerDAO;

    @Autowired //inject customerDAO to the customer service
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        this.customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int customerId) {
        return this.customerDAO.getCustomer(customerId);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        this.customerDAO.deleteCustomer(customer);
    }
}
