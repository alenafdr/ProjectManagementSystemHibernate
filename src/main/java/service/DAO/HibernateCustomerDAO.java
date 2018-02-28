package service.DAO;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateGeneric;

import java.util.ArrayList;
import java.util.List;

public class HibernateCustomerDAO extends HibernateGeneric implements GenericDAO<Customer, Integer>{

    public HibernateCustomerDAO() {
    }

    @Override
    public boolean save(Customer customer) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            session.save(customer);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public Customer getById(Integer id) {
        Customer result = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        result = (Customer) session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return result;

    }

    @Override
    public List<Customer> getAll() {
        List<Customer> result = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        result = (List<Customer>) session.createQuery("FROM Customer").list();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean update(Integer id, Customer customer) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Customer newCustomer = (Customer) session.get(Customer.class, id);
            newCustomer.setName(customer.getName());
            newCustomer.setProjects(customer.getProjects());
            session.update(newCustomer);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.close();
        return result;
    }

    @Override
    public boolean remove(Customer customer) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Customer newCustomer = (Customer) session.get(Customer.class, customer.getId());
            session.delete(newCustomer);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return result;
    }

}
