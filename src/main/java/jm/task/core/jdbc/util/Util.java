package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public  static ServiceRegistry getServiceRegistry(){
        return (ServiceRegistry) registry;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Properties settings = new Properties();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/preprjdb");
                settings.put("hibernate.connection.username", "web");
                settings.put("hibernate.connection.password", "12345678");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "none");

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class);


                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.buildSessionFactory();


            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                System.out.println(e.getMessage());
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
