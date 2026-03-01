package ma.projet.util;

import ma.projet.classes.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            System.out.println("Initialisation de Hibernate avec configuration programmatique...");

            Properties settings = new Properties();

            // Configuration de la base de données - Version MySQL 8
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // Notez le .cj pour MySQL 8
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/gestionproj?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "");

            // Configuration Hibernate
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect"); // Dialect pour MySQL 8
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.FORMAT_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            // Configuration du pool de connexions
            settings.put(Environment.C3P0_MIN_SIZE, "5");
            settings.put(Environment.C3P0_MAX_SIZE, "20");
            settings.put(Environment.C3P0_TIMEOUT, "300");
            settings.put(Environment.C3P0_MAX_STATEMENTS, "50");
            settings.put(Environment.C3P0_IDLE_TEST_PERIOD, "3000");

            Configuration configuration = new Configuration();
            configuration.setProperties(settings);

            // Ajout des classes annotées
            configuration.addAnnotatedClass(Projet.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Tache.class);
            configuration.addAnnotatedClass(EmployeeTache.class);

            // Construction de la SessionFactory
            sessionFactory = configuration.buildSessionFactory();
            System.out.println("SessionFactory créée avec succès !");

        } catch (Throwable ex) {
            System.err.println("Erreur lors de l'initialisation de Hibernate : " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory fermée.");
        }
    }
}