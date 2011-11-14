package ru.agiledev.aos.data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by a.kirillov
 * Date: 11/1/11 Time: 9:07 PM
 */
public class HibernateUtil {
  static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
         try {
             // Create the SessionFactory from hibernate.cfg.xml
             //Set configuration from config file
             Configuration cfg = new Configuration().configure();
             return cfg.buildSessionFactory();
         }
         catch (Throwable ex) {
             logger.trace("Initial SessionFactory creation failed." + ex, ex);
             throw new ExceptionInInitializerError(ex);
         }
     }
    /**
        * FactoryHolder is loaded on the first execution of Singleton.getInstance()
        * or the first access to FactoryHolder.INSTANCE, not before.
        */
       private static class FactoryHolder {
           public static final SessionFactory instance = buildSessionFactory();
       }

       public static SessionFactory getSessionFactory() {
           return FactoryHolder.instance;
       }

       public static void shutdown() {
         // Close caches and connection pools
         getSessionFactory().close();
     }
}
