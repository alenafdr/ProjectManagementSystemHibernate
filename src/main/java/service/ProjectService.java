package service;

import model.Project;
import service.DAO.HibernateProjectDAO;

import java.util.List;

public class ProjectService implements GenericService<Project, Integer> {
    HibernateProjectDAO jdbcProjectDAO = new HibernateProjectDAO();

    @Override
    public boolean save(Project project) {
        return jdbcProjectDAO.save(project);
    }

    @Override
    public Project getById(Integer integer) {
        return jdbcProjectDAO.getById(integer);
    }

    @Override
    public List<Project> getAll() {
        return jdbcProjectDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Project project) {
        return jdbcProjectDAO.update(integer, project);
    }

    @Override
    public boolean remove(Project project) {
        return jdbcProjectDAO.remove(project);
    }

    @Override
    public void stopSessionFactory() {
        jdbcProjectDAO.stopSessionFactory();
    }
}
