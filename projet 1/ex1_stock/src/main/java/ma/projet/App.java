package ma.projet;



import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App{
    public static void main(String[] args) {
        try {
            // Initialisation des services
            ProduitService produitService = new ProduitService();
            CategorieService categorieService = new CategorieService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            // Création des catégories
            System.out.println("=== Création des catégories ===");
            Categorie catInformatique = new Categorie("Informatique");
            Categorie catPeripherique = new Categorie("Périphérique");
            categorieService.create(catInformatique);
            categorieService.create(catPeripherique);
            System.out.println("Catégories créées avec succès");

            // Création des produits
            System.out.println("\n=== Création des produits ===");
            Produit p1 = new Produit("ES12", 120);
            p1.setCategorie(catInformatique);

            Produit p2 = new Produit("ZR85", 100);
            p2.setCategorie(catInformatique);

            Produit p3 = new Produit("EE85", 200);
            p3.setCategorie(catPeripherique);

            Produit p4 = new Produit("AB45", 80);
            p4.setCategorie(catPeripherique);

            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            System.out.println("Produits créés avec succès");

            // Affichage de tous les produits
            System.out.println("\n=== Liste de tous les produits ===");
            for (Produit p : produitService.findAll()) {
                System.out.println(p);
            }

            // Test 1: Produits par catégorie
            System.out.println("\n=== Test 1: Produits de la catégorie Informatique ===");
            List<Produit> produitsInformatique = produitService.getProduitsByCategorie(catInformatique);
            for (Produit p : produitsInformatique) {
                System.out.println(p);
            }

            // Création de commandes
            System.out.println("\n=== Création des commandes ===");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Commande cmd1 = new Commande(sdf.parse("2023-03-15"));
            Commande cmd2 = new Commande(sdf.parse("2023-04-20"));
            commandeService.create(cmd1);
            commandeService.create(cmd2);

            // Création des lignes de commande
            System.out.println("\n=== Création des lignes de commande ===");
            LigneCommandeProduit l1 = new LigneCommandeProduit(7, p1, cmd1);
            LigneCommandeProduit l2 = new LigneCommandeProduit(14, p2, cmd1);
            LigneCommandeProduit l3 = new LigneCommandeProduit(5, p3, cmd1);
            LigneCommandeProduit l4 = new LigneCommandeProduit(3, p4, cmd2);

            ligneCommandeService.create(l1);
            ligneCommandeService.create(l2);
            ligneCommandeService.create(l3);
            ligneCommandeService.create(l4);

            // Test 2: Produits commandés entre deux dates
            System.out.println("\n=== Test 2: Produits commandés entre le 01/03/2023 et le 31/03/2023 ===");
            Date debut = sdf.parse("2023-03-01");
            Date fin = sdf.parse("2023-03-31");
            List<Produit> produitsEntreDates = produitService.getProduitsCommandesEntreDates(debut, fin);
            for (Produit p : produitsEntreDates) {
                System.out.println(p);
            }

            // Test 3: Produits d'une commande spécifique avec affichage formaté
            System.out.println("\n=== Test 3: Produits de la commande du 15/03/2023 ===");
            System.out.println("Commande : CMD001 | Date : 15 Mars 2023");
            System.out.println("Liste des produits :");
            System.out.println("Référence\tPrix\tQuantité");
            System.out.println("--------------------------------");

            List<Object[]> produitsCommande = produitService.getProduitsByCommande(cmd1);
            for (Object[] row : produitsCommande) {
                System.out.printf("%s\t\t%.0f DH\t%d%n", row[0], row[1], row[2]);
            }

            // Test 4: Produits avec prix > 100 DH (requête nommée)
            System.out.println("\n=== Test 4: Produits avec prix > 100 DH (requête nommée) ===");
            List<Produit> produitsChers = produitService.getProduitsPrixSuperieur100();
            for (Produit p : produitsChers) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la session factory
            HibernateUtil.shutdown();
        }
    }
}