package com.helper;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    private static SessionFactory sessionFactory = null;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Initial SessionFactory creation is failed." + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}