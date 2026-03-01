package ma.projet.service;

import ma.projet.classes.Employee;
import ma.projet.classes.EmployeeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeService implements IDao<Employee> {

    @Override
    public boolean create(Employee employee) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(employee);
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
    public boolean update(Employee employee) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(employee);
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
    public boolean delete(Employee employee) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(employee);
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
    public Employee findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Employee> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche la liste des tâches réalisées par un employé
     * Tel que demandé dans l'énoncé
     */
    public void afficherTachesRealisees(int employeId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employe = session.get(Employee.class, employeId);

            if (employe != null) {
                System.out.println("Employé : " + employe.getId() + " - " +
                        employe.getNom() + " " + employe.getPrenom());
                System.out.println("Tâches réalisées:");
                System.out.println("Num\tTâche\t\tProjet\t\tDate Début Réelle\tDate Fin Réelle");

                if (employe.getEmployeeTaches() != null && !employe.getEmployeeTaches().isEmpty()) {
                    for (EmployeeTache et : employe.getEmployeeTaches()) {
                        Tache tache = et.getTache();
                        Projet projet = tache.getProjet();
                        System.out.println(tache.getId() + "\t" + tache.getNom() + "\t\t" +
                                projet.getNom() + "\t\t" +
                                et.getDateDebutReelle() + "\t\t" + et.getDateFinReelle());
                    }
                } else {
                    System.out.println("Aucune tâche réalisée par cet employé.");
                }
            } else {
                System.out.println("Aucun employé trouvé avec l'ID: " + employeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    /**
     * Affiche la liste des projets gérés par un employé
     * Tel que demandé dans l'énoncé
     */
    public void afficherProjetsGerer(int employeId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Employee employe = session.get(Employee.class, employeId);

            if (employe != null) {
                System.out.println("Employé : " + employe.getId() + " - " +
                        employe.getNom() + " " + employe.getPrenom());
                System.out.println("Projets gérés:");

                // Requête HQL pour trouver tous les projets où l'employé a travaillé
                Query<Projet> query = session.createQuery(
                        "SELECT DISTINCT p FROM Projet p JOIN p.taches t JOIN t.employeeTaches et WHERE et.employee.id = :employeId",
                        Projet.class);
                query.setParameter("employeId", employeId);

                List<Projet> projets = query.list();

                if (projets != null && !projets.isEmpty()) {
                    System.out.println("ID\tNom\t\tDate Début\tDate Fin");
                    for (Projet p : projets) {
                        System.out.println(p.getId() + "\t" + p.getNom() + "\t\t" +
                                p.getDateDebut() + "\t" + p.getDateFin());
                    }
                } else {
                    System.out.println("Aucun projet géré par cet employé.");
                }
            } else {
                System.out.println("Aucun employé trouvé avec l'ID: " + employeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}
