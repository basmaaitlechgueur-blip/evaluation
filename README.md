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

---
--
    🧪 Programme de Test
    ---
Test.java (ma.projet.test)
---
Teste toutes les fonctionnalités :
---

✅ Création catégories

✅ Création produits

✅ Création commandes

✅ Création lignes de commande

✅ Affichage formaté des résultats

---

💡 Concepts clés utilisés
---

JPA Annotations : @Entity, @Id, @GeneratedValue, @OneToMany, @ManyToOne

Hibernate : ORM, SessionFactory, Session, Transaction

Relations : ManyToOne, OneToMany, table d'association

Requêtes : HQL, NamedQuery

Patterns : DAO, Singleton

---

# Captures d ecrans:
---
<img width="1920" height="1080" alt="Screenshot (459)" src="https://github.com/user-attachments/assets/c6fd95cd-3ef1-4a42-ba1a-f1a02660ed17" />

<img width="1920" height="1080" alt="Screenshot (460)" src="https://github.com/user-attachments/assets/561a47ad-4643-45f5-b8c6-fa9a607990a2" />

<img width="1920" height="1080" alt="Screenshot (461)" src="https://github.com/user-attachments/assets/f84b0abc-a506-4f44-b930-9c581835bd00" />

<img width="1920" height="1080" alt="Screenshot (462)" src="https://github.com/user-attachments/assets/bed8a00e-10f6-4d03-8fd5-6bc029b12716" />

<img width="1920" height="1080" alt="Screenshot (463)" src="https://github.com/user-attachments/assets/9bdc9f4a-398e-42cb-959b-be11aee2878f" />

<img width="1920" height="1080" alt="Screenshot (464)" src="https://github.com/user-attachments/assets/2773d1af-19d3-419d-8bd4-1e759b8d0a9d" />

<img width="1920" height="1080" alt="Screenshot (465)" src="https://github.com/user-attachments/assets/0c01973c-db95-4e7d-aba2-5fb20d668031" />

<img width="1920" height="1080" alt="Screenshot (466)" src="https://github.com/user-attachments/assets/5975c1ad-6116-4a12-8806-a4ed5ebc6a0f" />

<img width="1920" height="1080" alt="Screenshot (467)" src="https://github.com/user-attachments/assets/38e8a9d8-940a-404a-a330-a3159d31f894" />

<img width="1920" height="1080" alt="Screenshot (468)" src="https://github.com/user-attachments/assets/25b2a670-4a64-4654-a409-6569e22e3d80" />

<img width="1920" height="1080" alt="Screenshot (469)" src="https://github.com/user-attachments/assets/0c101027-237f-4a14-a8c6-6a7bf1222472" />

<img width="1920" height="1080" alt="Screenshot (470)" src="https://github.com/user-attachments/assets/9070dbfa-0213-4cfd-bcc5-a33b0e44e178" />


----



# PROJET 2 : Application de Gestion de Projets

---

📋 Description

---

Application Java de gestion de projets utilisant Hibernate pour la persistance des données. Elle permet de suivre les projets, les employés, les tâches et le temps réel passé sur chaque tâche.

---
🔗 Relations entre les tables
---

Projet 1 ---→ N Tache : Un projet peut avoir plusieurs tâches

Tache N ---→ 1 Projet : Une tâche appartient à un seul projet

Employee 1 ---→ N EmployeeTache : Un employé peut avoir plusieurs affectations

Tache 1 ---→ N EmployeeTache : Une tâche peut avoir plusieurs employés affectés

---
🚀 Fonctionnalités implémentées
---

Dans EmployeService
---
afficherTachesRealisees(int employeId) : Liste les tâches réalisées par un employé

afficherProjetsGerer(int employeId) : Liste les projets où l'employé a travaillé

Dans ProjetService
---
afficherTachesPlanifiees(int projetId) : Liste les tâches prévues pour un projet

afficherTachesRealisees(int projetId) : Liste les tâches réalisées avec dates réelles

Dans TacheService
---
afficherTachesPrixSuperieur(double prix) : Tâches avec prix > 1000 DH (requête nommée)

afficherTachesEntreDates(Date debut, Date fin) : Tâches réalisées entre deux dates

---
