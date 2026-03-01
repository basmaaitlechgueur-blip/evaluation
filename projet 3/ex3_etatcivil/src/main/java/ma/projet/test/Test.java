package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        try {
            // Initialisation des services
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // 1. Création de 10 femmes
            System.out.println("************ CRÉATION DES FEMMES ************");
            Femme[] femmes = new Femme[10];
            femmes[0] = new Femme("RAMI", "SALIMA", "0612345678", "Casablanca", sdf.parse("15/03/1990"));
            femmes[1] = new Femme("ZEGDAOUI", "AMAL", "0623456789", "Rabat", sdf.parse("20/07/1988"));
            femmes[2] = new Femme("ALAOUI", "WAFA", "0634567890", "Fès", sdf.parse("10/11/1992"));
            femmes[3] = new Femme("ALAMI", "KARIMA", "0645678901", "Marrakech", sdf.parse("05/09/1985"));
            femmes[4] = new Femme("BENANI", "FATIMA", "0656789012", "Tanger", sdf.parse("25/12/1995"));
            femmes[5] = new Femme("CHRAIBI", "NADIA", "0667890123", "Agadir", sdf.parse("30/04/1987"));
            femmes[6] = new Femme("DOUKKALI", "SAMIRA", "0678901234", "Oujda", sdf.parse("12/08/1991"));
            femmes[7] = new Femme("EL FASSI", "SOUMIA", "0689012345", "Meknès", sdf.parse("18/02/1989"));
            femmes[8] = new Femme("GHANNAM", "HIND", "0690123456", "Tétouan", sdf.parse("22/06/1993"));
            femmes[9] = new Femme("HARRATI", "NAIMA", "0601234567", "Safi", sdf.parse("07/10/1986"));

            for (int i = 0; i < femmes.length; i++) {
                if (femmeService.create(femmes[i])) {
                    System.out.println("Femme créée: " + femmes[i].getPrenom() + " " + femmes[i].getNom());
                }
            }

            // 2. Création de 5 hommes
            System.out.println("\n************ CRÉATION DES HOMMES ************");
            Homme[] hommes = new Homme[5];
            hommes[0] = new Homme("SAFI", "SAAD", "0712345678", "Casablanca", sdf.parse("10/02/1985"));
            hommes[1] = new Homme("BENJELLOUN", "MOHAMED", "0723456789", "Rabat", sdf.parse("15/06/1983"));
            hommes[2] = new Homme("EL IDRISSI", "HASSAN", "0734567890", "Fès", sdf.parse("20/09/1987"));
            hommes[3] = new Homme("TOUIMI", "AHMED", "0745678901", "Marrakech", sdf.parse("25/12/1982"));
            hommes[4] = new Homme("ZIANI", "YOUSSEF", "0756789012", "Tanger", sdf.parse("30/04/1988"));

            for (int i = 0; i < hommes.length; i++) {
                if (hommeService.create(hommes[i])) {
                    System.out.println("Homme créé: " + hommes[i].getPrenom() + " " + hommes[i].getNom());
                }
            }

            // 3. Création des mariages pour SAFI SAID (hommes[0])
            System.out.println("\n************ CRÉATION DES MARIAGES POUR SAFI SAID ************");
            Homme hommeExemple = hommes[0]; // SAFI SAID

            // Mariages en cours
            Mariage m1 = new Mariage(hommeExemple, femmes[0], sdf.parse("03/09/1990"), null, 4);
            Mariage m2 = new Mariage(hommeExemple, femmes[1], sdf.parse("03/09/1995"), null, 2);
            Mariage m3 = new Mariage(hommeExemple, femmes[2], sdf.parse("04/11/2000"), null, 3);

            // Mariage échoué
            Mariage m4 = new Mariage(hommeExemple, femmes[3], sdf.parse("03/09/1989"), sdf.parse("01/01/1995"), 0);

            // Ajout de quelques mariages supplémentaires pour les autres hommes (pour les tests)
            Mariage m5 = new Mariage(hommes[1], femmes[4], sdf.parse("10/05/2010"), null, 2);
            Mariage m6 = new Mariage(hommes[1], femmes[5], sdf.parse("15/08/2015"), null, 1);
            Mariage m7 = new Mariage(hommes[2], femmes[6], sdf.parse("20/01/2012"), sdf.parse("20/01/2020"), 3);
            Mariage m8 = new Mariage(hommes[2], femmes[7], sdf.parse("25/03/2021"), null, 0);

            if (mariageService.create(m1)) System.out.println("Mariage 1 créé: SAID avec SALIMA");
            if (mariageService.create(m2)) System.out.println("Mariage 2 créé: SAID avec AMAL");
            if (mariageService.create(m3)) System.out.println("Mariage 3 créé: SAID avec WAFA");
            if (mariageService.create(m4)) System.out.println("Mariage 4 créé: SAID avec KARIMA (échoué)");
            if (mariageService.create(m5)) System.out.println("Mariage 5 créé");
            if (mariageService.create(m6)) System.out.println("Mariage 6 créé");
            if (mariageService.create(m7)) System.out.println("Mariage 7 créé");
            if (mariageService.create(m8)) System.out.println("Mariage 8 créé");

            // 4. Afficher la liste des femmes
            System.out.println("\n************ LISTE DES FEMMES ************");
            List<Femme> toutesFemmes = femmeService.findAll();
            for (Femme f : toutesFemmes) {
                System.out.println(f.getId() + " - " + f.getPrenom() + " " + f.getNom() +
                        " - Née le: " + sdf.format(f.getDateNaissance()) +
                        " - Tél: " + f.getTelephone() +
                        " - Adresse: " + f.getAdresse());
            }

            // 5. Afficher la femme la plus âgée
            System.out.println("\n************ FEMME LA PLUS ÂGÉE ************");
            Femme plusAgee = femmeService.getFemmeLaPlusAgee();
            if (plusAgee != null) {
                System.out.println("La femme la plus âgée est: " + plusAgee.getPrenom() + " " + plusAgee.getNom() +
                        " - Née le: " + sdf.format(plusAgee.getDateNaissance()));
            }

            // 6. Afficher les épouses d'un homme donné
            System.out.println("\n************ ÉPOUSES DE SAFI SAID ENTRE 01/01/1990 ET 31/12/2000 ************");
            Date debut = sdf.parse("01/01/1990");
            Date fin = sdf.parse("31/12/2000");
            List<Femme> epouses = hommeService.getEpousesEntreDates(hommeExemple.getId(), debut, fin);
            System.out.println("Homme: " + hommeExemple.getPrenom() + " " + hommeExemple.getNom());
            System.out.println("Période du " + sdf.format(debut) + " au " + sdf.format(fin));
            System.out.println("Épouses trouvées (" + epouses.size() + "):");
            for (Femme f : epouses) {
                System.out.println("  - " + f.getPrenom() + " " + f.getNom());
            }

            // 7. Afficher le nombre d'enfants d'une femme entre deux dates
            System.out.println("\n************ NOMBRE D'ENFANTS DE SALIMA RAMI ENTRE 01/01/1990 ET 31/12/2000 ************");
            int nbEnfants = femmeService.getNombreEnfantsEntreDates(femmes[0].getId(), debut, fin);
            System.out.println("Femme: " + femmes[0].getPrenom() + " " + femmes[0].getNom());
            System.out.println("Période du " + sdf.format(debut) + " au " + sdf.format(fin));
            System.out.println("Nombre total d'enfants: " + nbEnfants);

            // 8. Afficher les femmes mariées deux fois ou plus
            System.out.println("\n************ FEMMES MARIÉES DEUX FOIS OU PLUS ************");
            List<Femme> femmesMarieesDeuxFois = femmeService.getFemmesMarieesDeuxFois();
            if (femmesMarieesDeuxFois.isEmpty()) {
                System.out.println("Aucune femme mariée deux fois ou plus trouvée.");
            } else {
                for (Femme f : femmesMarieesDeuxFois) {
                    System.out.println(f.getPrenom() + " " + f.getNom() + " - Nombre de mariages: " + f.getMariages().size());
                }
            }

            // 9. Afficher les hommes mariés à quatre femmes entre deux dates
            System.out.println("\n************ HOMMES MARIÉS À 4 FEMMES ENTRE 01/01/1990 ET 31/12/2000 ************");
            long nbHommes = femmeService.getHommesMarieesQuatreFemmes(debut, fin);
            System.out.println("Nombre d'hommes mariés à exactement 4 femmes pendant cette période: " + nbHommes);

            // 10. Afficher les mariages d'un homme avec tous les détails (comme dans l'exemple)
            System.out.println("\n************ DÉTAILS DES MARIAGES DE SAFI SAID ************");
            hommeService.afficherMariagesDetails(hommeExemple.getId());

            // 11. Test supplémentaire: recherche interactive (optionnel)
            System.out.println("\n************ TEST INTERACTIF (OPTIONNEL) ************");
            System.out.println("Voulez-vous tester la recherche par ID? (oui/non)");
            Scanner scanner = new Scanner(System.in);
            String reponse = scanner.nextLine();

            if (reponse.equalsIgnoreCase("oui")) {
                System.out.print("Entrez l'ID de l'homme à rechercher: ");
                int id = scanner.nextInt();
                Homme homme = hommeService.findById(id);
                if (homme != null) {
                    hommeService.afficherMariagesDetails(id);
                } else {
                    System.out.println("Aucun homme trouvé avec l'ID " + id);
                }
            }

            // Résumé final
            System.out.println("\n************ RÉSUMÉ DES OPÉRATIONS ************");
            System.out.println("Total femmes créées: " + femmeService.findAll().size());
            System.out.println("Total hommes créés: " + hommeService.findAll().size());
            System.out.println("Total mariages créés: " + mariageService.findAll().size());

            System.out.println("\n************ TEST TERMINÉ AVEC SUCCÈS ************");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution du test: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture de la session Hibernate
            HibernateUtil.shutdown();
        }
    }
}
