package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("========== DÉBUT DU TEST ==========");

            // Initialisation des services
            ProjetService projetService = new ProjetService();
            EmployeService employeService = new EmployeService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 1. Création des projets
            System.out.println("\n--- Création des projets ---");
            Projet projet1 = new Projet("Gestion de stock", sdf.parse("2015-01-13"), sdf.parse("2015-06-30"));
            Projet projet2 = new Projet("Application web", sdf.parse("2015-02-01"), sdf.parse("2015-07-31"));

            projetService.create(projet1);
            projetService.create(projet2);
            System.out.println("Projets créés avec succès");
            System.out.println("ID du projet 1: " + projet1.getId());
            System.out.println("ID du projet 2: " + projet2.getId());

            // 2. Création des employés
            System.out.println("\n--- Création des employés ---");
            Employee emp1 = new Employee("Atouil", "salma", "0612345678");
            Employee emp2 = new Employee("Malaki", "Maria", "0687654321");

            employeService.create(emp1);
            employeService.create(emp2);
            System.out.println("Employés créés avec succès");
            System.out.println("ID de l'employé 1: " + emp1.getId());
            System.out.println("ID de l'employé 2: " + emp2.getId());

            // 3. Création des tâches pour le projet 1
            System.out.println("\n--- Création des tâches ---");
            Tache tache1 = new Tache("Analyse", sdf.parse("2015-02-10"), sdf.parse("2015-02-20"), 5000, projet1);
            Tache tache2 = new Tache("Conception", sdf.parse("2013-03-10"), sdf.parse("2013-03-15"), 4000, projet1);
            Tache tache3 = new Tache("Développement", sdf.parse("2013-04-10"), sdf.parse("2013-04-25"), 8000, projet1);

            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            System.out.println("Tâches créées avec succès");

            // 4. Affectation des employés aux tâches
            System.out.println("\n--- Affectation des employés aux tâches ---");
            EmployeeTache et1 = new EmployeeTache(emp1, tache1, sdf.parse("2015-02-10"), sdf.parse("2015-02-20"));
            EmployeeTache et2 = new EmployeeTache(emp1, tache2, sdf.parse("2013-03-10"), sdf.parse("2013-03-15"));
            EmployeeTache et3 = new EmployeeTache(emp2, tache3, sdf.parse("2013-04-10"), sdf.parse("2013-04-25"));

            employeTacheService.create(et1);
            employeTacheService.create(et2);
            employeTacheService.create(et3);
            System.out.println("Affectations créées avec succès");

            // 5. Tests des méthodes demandées
            System.out.println("\n========== TEST 1: Tâches planifiées pour le projet 1 ==========");
            // Utilisation de la méthode afficherTachesPlanifiees du ProjetService
            projetService.afficherTachesPlanifiees(projet1.getId());

            System.out.println("\n========== TEST 2: Tâches réalisées pour le projet 1 ==========");
            // Utilisation de la méthode afficherTachesRealisees du ProjetService
            projetService.afficherTachesRealisees(projet1.getId());

            System.out.println("\n========== TEST 3: Tâches réalisées par l'employé 1 ==========");
            // Utilisation de la méthode afficherTachesRealisees du EmployeService
            employeService.afficherTachesRealisees(emp1.getId());

            System.out.println("\n========== TEST 4: Projets gérés par l'employé 1 ==========");
            // Utilisation de la méthode afficherProjetsGerer du EmployeService
            employeService.afficherProjetsGerer(emp1.getId());

            System.out.println("\n========== TEST 5: Tâches avec prix > 1000 DH ==========");
            // Utilisation de la méthode afficherTachesPrixSuperieur du TacheService
            tacheService.afficherTachesPrixSuperieur(1000);

            System.out.println("\n========== TEST 6: Tâches réalisées entre deux dates ==========");
            // Utilisation de la méthode afficherTachesEntreDates du TacheService
            tacheService.afficherTachesEntreDates(sdf.parse("2013-02-01"), sdf.parse("2013-04-30"));

            System.out.println("\n========== FIN DU TEST ==========");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution du test: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture de la session factory
            HibernateUtil.shutdown();
            System.out.println("SessionFactory fermée.");
        }
    }
}