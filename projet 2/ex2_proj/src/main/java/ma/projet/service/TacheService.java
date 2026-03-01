package ma.projet.service;

import ma.projet.classes.EmployeeTache;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public boolean create(Tache tache) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(tache);
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
    public boolean update(Tache tache) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(tache);
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
    public boolean delete(Tache tache) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(tache);
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
    public Tache findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Tache.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Tache> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Tache> query = session.createQuery("FROM Tache", Tache.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche les tâches dont le prix est supérieur à 1000 DH
     * Utilise une requête nommée comme demandé
     */
    public void afficherTachesPrixSuperieur() {
        afficherTachesPrixSuperieur(1000); // Par défaut 1000 DH
    }

    public void afficherTachesPrixSuperieur(double prix) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Tache> query = session.createNamedQuery("Tache.findByPrixSuperieur", Tache.class);
            query.setParameter("prix", prix);

            List<Tache> taches = query.list();

            System.out.println("Tâches avec prix supérieur à " + prix + " DH:");
            System.out.println("ID\tNom\t\tPrix\tProjet");

            if (taches != null && !taches.isEmpty()) {
                for (Tache t : taches) {
                    System.out.println(t.getId() + "\t" + t.getNom() + "\t\t" +
                            t.getPrix() + "\t" + t.getProjet().getNom());
                }
            } else {
                System.out.println("Aucune tâche trouvée avec un prix > " + prix + " DH");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche les tâches réalisées entre deux dates
     */
    public void afficherTachesEntreDates(Date dateDebut, Date dateFin) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Tache> query = session.createQuery(
                    "SELECT DISTINCT t FROM Tache t JOIN t.employeeTaches et " +
                            "WHERE et.dateDebutReelle >= :dateDebut AND et.dateFinReelle <= :dateFin",
                    Tache.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            List<Tache> taches = query.list();

            System.out.println("Tâches réalisées entre " + dateDebut + " et " + dateFin + ":");
            System.out.println("ID\tNom\t\tProjet\t\tDate Début Réelle\tDate Fin Réelle");

            if (taches != null && !taches.isEmpty()) {
                for (Tache t : taches) {
                    for (EmployeeTache et : t.getEmployeeTaches()) {
                        System.out.println(t.getId() + "\t" + t.getNom() + "\t\t" +
                                t.getProjet().getNom() + "\t\t" +
                                et.getDateDebutReelle() + "\t\t" + et.getDateFinReelle());
                    }
                }
            } else {
                System.out.println("Aucune tâche trouvée entre ces dates.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}