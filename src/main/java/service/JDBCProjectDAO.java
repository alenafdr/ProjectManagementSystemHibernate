package service;

import model.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JDBCProjectDAO extends JDBCGeneric implements GenericDAO<Project, Integer>{

    public JDBCProjectDAO() {
    }

    @Override
    public boolean save(Project project){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            session.save(project);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public Project getById(Integer id){
        Project result = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        result = (Project) session.get(Project.class, id);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<Project> getAll(){
        List<Project> result = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        result = (List<Project>) session.createQuery("FROM Project").list();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean update(Integer id, Project project){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Project newProject = (Project) session.get(Project.class, id);
            newProject.setName(project.getName());
            newProject.setDevelopers(project.getDevelopers());
            session.update(newProject);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.close();
        return result;
    }

    @Override
    public boolean remove(Project project){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Project newProject = (Project) session.get(Project.class, project.getId());
            session.delete(newProject);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return result;
    }
}
