package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public boolean create(Projet projet) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(projet);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Projet projet) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(projet);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Projet projet) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(projet);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Projet findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Projet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Projet> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Projet> query = session.createQuery("FROM Projet", Projet.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche la liste des tâches planifiées pour un projet
     * Tel que demandé dans l'énoncé
     */
    public void afficherTachesPlanifiees(int projetId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Projet projet = session.get(Projet.class, projetId);

            if (projet != null) {
                System.out.println("Projet : " + projet.getId() + " | Nom : " + projet.getNom() +
                        " | Date début : " + projet.getDateDebut() +
                        " | Date fin : " + projet.getDateFin());
                System.out.println("Liste des tâches planifiées:");
                System.out.println("Num\tNom\t\tDate Début\tDate Fin");

                for (Tache tache : projet.getTaches()) {
                    System.out.println(tache.getId() + "\t" + tache.getNom() + "\t\t" +
                            tache.getDateDebut() + "\t" + tache.getDateFin());
                }
            } else {
                System.out.println("Aucun projet trouvé avec l'ID: " + projetId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche la liste des tâches réalisées avec les dates réelles
     * Tel que demandé dans l'énoncé
     */
    public void afficherTachesRealisees(int projetId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Projet projet = session.get(Projet.class, projetId);

            if (projet != null) {
                System.out.println("Projet : " + projet.getId() + " | Nom : " + projet.getNom() +
                        " | Date début : " + projet.getDateDebut() +
                        " | Date fin : " + projet.getDateFin());
                System.out.println("Liste des tâches réalisées:");
                System.out.println("Num\tNom\t\tDate Début Réelle\tDate Fin Réelle");

                boolean hasRealTasks = false;
                for (Tache tache : projet.getTaches()) {
                    for (EmployeeTache et : tache.getEmployeeTaches()) {
                        System.out.println(tache.getId() + "\t" + tache.getNom() + "\t\t" +
                                et.getDateDebutReelle() + "\t\t" + et.getDateFinReelle());
                        hasRealTasks = true;
                    }
                }

                if (!hasRealTasks) {
                    System.out.println("Aucune tâche réalisée pour ce projet.");
                }
            } else {
                System.out.println("Aucun projet trouvé avec l'ID: " + projetId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
