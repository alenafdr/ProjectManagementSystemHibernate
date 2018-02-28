package service.DAO;


import model.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateGeneric;

import java.util.ArrayList;
import java.util.List;

public class HibernateSkillDAO extends HibernateGeneric implements GenericDAO<Skill, Integer>{

    @Override
    public boolean save(Skill skill){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            session.save(skill);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public Skill getById(Integer id){
        Skill result = null;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        result = (Skill) session.get(Skill.class, id);
        transaction.commit();
        session.close();

        return result;
    }

    @Override
    public List<Skill> getAll(){
        List<Skill> result = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        result = (List<Skill>) session.createQuery("FROM Skill").list();

        transaction.commit();
        session.close();

        return result;
    }

    @Override
    public boolean update(Integer id, Skill skill){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Skill newSkill = (Skill) session.get(Skill.class, id);
            newSkill.setName(skill.getName());
            session.update(newSkill);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.close();
        return result;
    }

    @Override
    public boolean remove(Skill skill){
        boolean result = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        try {
            Skill newSkill = (Skill) session.get(Skill.class, skill.getId());
            session.delete(newSkill);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return result;
    }
}
