package service;

import model.Company;
import service.DAO.HibernateCompanyDAO;

import java.util.List;

public class CompanyService implements GenericService<Company, Integer> {

    HibernateCompanyDAO jdbcCompanyDAO = new HibernateCompanyDAO();

    @Override
    public boolean save(Company company) {
        return jdbcCompanyDAO.save(company);
    }

    @Override
    public Company getById(Integer integer) {
        return jdbcCompanyDAO.getById(integer);
    }

    @Override
    public List<Company> getAll() {
        return jdbcCompanyDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Company company) {
        return jdbcCompanyDAO.update(integer, company);
    }

    @Override
    public boolean remove(Company company) {
        return jdbcCompanyDAO.remove(company);
    }

    @Override
    public void stopSessionFactory() {
        jdbcCompanyDAO.stopSessionFactory();
    }
}
