package service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class JDBCGeneric {

    private static SessionFactory sessionFactory;

    public JDBCGeneric() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected void stopSessionFactory(){
        sessionFactory.close();
    }

}
