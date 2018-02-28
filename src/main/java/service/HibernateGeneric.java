package service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibernateGeneric {

    private static SessionFactory sessionFactory;

    public HibernateGeneric() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected void stopSessionFactory(){
        sessionFactory.close();
    }

}
