package service;

import model.Developer;
import service.DAO.HibernateDeveloperDAO;

import java.util.List;

public class DeveloperService implements GenericService<Developer, Integer> {

    HibernateDeveloperDAO jdbcDeveloperDAO = new HibernateDeveloperDAO();

    @Override
    public boolean save(Developer developer) {
        return jdbcDeveloperDAO.save(developer);
    }

    @Override
    public Developer getById(Integer integer) {
        return jdbcDeveloperDAO.getById(integer);
    }

    @Override
    public List<Developer> getAll() {
        return jdbcDeveloperDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Developer developer) {
        return jdbcDeveloperDAO.update(integer, developer);
    }

    @Override
    public boolean remove(Developer developer) {
        return jdbcDeveloperDAO.remove(developer);
    }

    @Override
    public void stopSessionFactory() {
        jdbcDeveloperDAO.stopSessionFactory();
    }
}
