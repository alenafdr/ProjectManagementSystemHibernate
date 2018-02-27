package service;

import model.Developer;
import model.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JDBCDeveloperDAO extends JDBCGeneric implements GenericDAO<Developer, Integer>{

    public JDBCDeveloperDAO() {
    }

    @Override
    public boolean save(Developer developer){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            session.save(developer);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public Developer getById(Integer id){
        Developer result = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        result = (Developer) session.get(Developer.class, id);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<Developer> getAll(){
        List<Developer> result = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        result = (List<Developer>) session.createQuery("FROM Developer").list();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean update(Integer id, Developer developer){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Developer newDeveloper = (Developer) session.get(Developer.class, id);
            newDeveloper.setName(developer.getName());
            newDeveloper.setSkills(developer.getSkills());
            session.update(newDeveloper);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.close();
        return result;
    }

    @Override
    public boolean remove(Developer developer){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Developer newDeveloper = (Developer) session.get(Developer.class, developer.getId());
            session.delete(newDeveloper);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return result;
    }
}
