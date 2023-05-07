package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.bo.HibernateUtil;
import com.devstack.pos.dao.custom.CustomerDao;
import com.devstack.pos.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    public void save(Customer customer) {
        try (Session session = HibernateUtil.openSession()) {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        }
    }

    public Customer find(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            return session.find(Customer.class, id);
        }
    }

    public void update(Customer customer) throws ClassNotFoundException {
        try (Session session = HibernateUtil.openSession()) {
            Customer selectedCustomer = session.find(Customer.class, customer.getCustomerId());
            if (selectedCustomer != null) {
                selectedCustomer.setName(customer.getName());
                selectedCustomer.setAddress(customer.getAddress());
                selectedCustomer.setSalary(customer.getSalary());
                session.beginTransaction();
                session.save(selectedCustomer);
                session.getTransaction().commit();
                return;
            }
        }
        throw new ClassNotFoundException("Customer Not Found");
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            session.delete(
                    session.find(Customer.class, id)
            );
        }
    }

    public List findAll() {
        try (Session session = HibernateUtil.openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
            return query.list();
        }
    }
}
