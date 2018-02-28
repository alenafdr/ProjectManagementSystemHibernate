package service;

import model.Customer;
import service.DAO.HibernateCustomerDAO;

import java.util.List;

public class CustomerService implements GenericService<Customer, Integer> {
    HibernateCustomerDAO jdbcCustomerDAO;
    public CustomerService() {
        jdbcCustomerDAO = new HibernateCustomerDAO();
    }

    @Override
    public boolean save(Customer customer) {
        return jdbcCustomerDAO.save(customer);
    }

    @Override
    public Customer getById(Integer integer) {
        return jdbcCustomerDAO.getById(integer);
    }

    @Override
    public List<Customer> getAll() {
        return jdbcCustomerDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Customer customer) {
        return false;
    }

    @Override
    public boolean remove(Customer customer) {
        return jdbcCustomerDAO.remove(customer);
    }

    @Override
    public void stopSessionFactory() {
        jdbcCustomerDAO.stopSessionFactory();
    }
}
