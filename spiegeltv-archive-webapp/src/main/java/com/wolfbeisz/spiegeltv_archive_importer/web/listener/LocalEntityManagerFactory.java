package com.wolfbeisz.spiegeltv_archive_importer.web.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// from: https://blogs.sap.com/2012/12/11/put-jpa-in-your-web-app-tomcat-eclipselink/
@WebListener
public class LocalEntityManagerFactory implements ServletContextListener{
    private static EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }
}
