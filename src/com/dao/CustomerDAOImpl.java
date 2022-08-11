package com.dao;

import com.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

//    @Autowired
    private SessionFactory sessionFactory;

    @Autowired //injecting Hibernate's sessionFactory
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
//    @Transactional //no need to beginTransaction and endTransaction
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("select t from Customer t order by t.lastName", Customer.class);
        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Customer customerById = session.get(Customer.class, customerId);
        return customerById;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(customer);
    }
}
