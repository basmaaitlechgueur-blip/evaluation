# PROJET 1 : Gestion de Stock - Application Java/JPA
---

📋 Description
---

Application de gestion de stock pour un magasin de produits informatiques utilisant JPA/Hibernate pour la persistance des données.

---

🏗 Structure du Projet
---
src/
└── ma/
    └── projet/
        ├── classes/          # Entités JPA
        ├── dao/              # Interface générique
        ├── service/          # Services (CRUD)
        ├── util/             # HibernateUtil
        └── test/              # Programme de test

---

📦 Entités (ma.projet.classes)
---

1. Produit
2.   Categorie
3.    Commande
4.  LigneCommandeProduit
  
---

✅ Fonctionnalités Implémentées
---
ProduitService - Méthodes spéciales
---
getProduitsByCategorie : Liste des produits par catégorie

getProduitsCommandesEntreDates : Produits commandés entre deux dates

getProduitsByCommande : Produits d'une commande avec quantités

getProduitsPrixSuperieur100 : Produits > 100 DH (requête nommée)
    
