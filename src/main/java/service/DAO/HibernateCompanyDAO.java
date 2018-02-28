package service.DAO;

import model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateGeneric;

import java.util.ArrayList;
import java.util.List;

public class HibernateCompanyDAO extends HibernateGeneric implements GenericDAO<Company, Integer>{

    public HibernateCompanyDAO(){
    }

    @Override
    public boolean save(Company company) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            session.save(company);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public Company getById(Integer id){
        Company result = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        result = (Company) session.get(Company.class, id);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<Company> getAll(){
        List<Company> result = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        result = (List<Company>) session.createQuery("FROM Company").list();

        transaction.commit();
        session.close();
        return result;
    }
    @Override
    public boolean update(Integer id, Company company) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            Company newCompany = (Company) session.get(Company.class, id);
            newCompany.setName(company.getName());
            newCompany.setProjects(company.getProjects());
            session.update(newCompany);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public boolean remove(Company company) {
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            Company newCompany = (Company) session.get(Company.class, company.getId());
            session.delete(newCompany);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }
}
