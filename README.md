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
# Capture d ecrans :
---
<img width="1920" height="1080" alt="Screenshot (497)" src="https://github.com/user-attachments/assets/f1f557d6-c722-4205-9635-af0c8267c416" />

<img width="1920" height="1080" alt="Screenshot (498)" src="https://github.com/user-attachments/assets/6df71c3f-ef2f-493f-a695-768f902567fa" />

<img width="1920" height="1080" alt="Screenshot (499)" src="https://github.com/user-attachments/assets/3153d3dd-b371-4b07-9e59-2cc1abaafc6e" />

<img width="1920" height="1080" alt="Screenshot (500)" src="https://github.com/user-attachments/assets/98eb8ff4-2af4-4680-875b-99109774f6b6" />

<img width="1920" height="1080" alt="Screenshot (501)" src="https://github.com/user-attachments/assets/bf0c0451-21f6-42c3-ae7f-e6327c5cec95" />

<img width="1920" height="1080" alt="Screenshot (502)" src="https://github.com/user-attachments/assets/28eccd3e-7788-4d21-ad4f-991c100fa440" />

<img width="1920" height="1080" alt="Screenshot (503)" src="https://github.com/user-attachments/assets/e874903d-f845-4774-8036-88aa5dea4c48" />

<img width="1920" height="1080" alt="Screenshot (504)" src="https://github.com/user-attachments/assets/a02faa86-dce3-4829-9c68-2e9a7f499a34" />

<img width="1920" height="1080" alt="Screenshot (505)" src="https://github.com/user-attachments/assets/7fd84a31-e669-4026-8960-dbdcea99d815" />

<img width="1920" height="1080" alt="Screenshot (506)" src="https://github.com/user-attachments/assets/1a5f5d06-b969-4347-ab7d-26d9f49fbd15" />

<img width="1920" height="1080" alt="Screenshot (508)" src="https://github.com/user-attachments/assets/54dfbfdc-fe79-48c4-a8ff-9d596352c3e5" />

<img width="1920" height="1080" alt="Screenshot (509)" src="https://github.com/user-attachments/assets/248e639d-4d80-463b-beb0-b3b65cb84da7" />

<img width="1920" height="1080" alt="Screenshot (510)" src="https://github.com/user-attachments/assets/34d3a4a2-c665-439e-9c02-9790be23d77f" />

<img width="1920" height="1080" alt="Screenshot (511)" src="https://github.com/user-attachments/assets/48814c33-af26-4328-b07f-d54b3ae58ffb" />

<img width="1920" height="1080" alt="Screenshot (512)" src="https://github.com/user-attachments/assets/b0c07f26-f23e-4b3e-b88f-54d95f9fb707" />

<img width="1920" height="1080" alt="Screenshot (513)" src="https://github.com/user-attachments/assets/5a6e5f02-17df-4a6d-8f7c-f29052b85166" />

<img width="1920" height="1080" alt="Screenshot (514)" src="https://github.com/user-attachments/assets/a6cdbfd3-2909-43da-8def-97d60919b70b" />

<img width="1920" height="1080" alt="Screenshot (515)" src="https://github.com/user-attachments/assets/48da1531-9a7c-4074-b0b1-ffb7fed6492b" />

---

# PROJET 3 : Application de Gestion d'État Civil
---

# Description
---
Application Java de gestion des citoyens et de leurs relations matrimoniales dans une province. 
Elle permet de gérer les hommes, les femmes et leurs mariages avec persistance des données dans MySQL.

---
## Technologies Utilisées
---
- Java 8+
- Hibernate (ORM)
- MySQL
- Maven (gestion des dépendances)
- JPA (Java Persistence API)
- ---
## Structure du Projet
---

ex3_etatcivil/
├── src/
│ └── main/
│ └── java/
│ └── ma/
│ └── projet/
│ ├── beans/
│ │ ├── Personne.java
│ │ ├── Homme.java
│ │ ├── Femme.java
│ │ └── Mariage.java
│ ├── dao/
│ │ └── IDao.java
│ ├── service/
│ │ ├── AbstractFacade.java
│ │ ├── HommeService.java
│ │ ├── FemmeService.java
│ │ └── MariageService.java
│ ├── util/
│ │ └── HibernateUtil.java
│ └── test/
│ └── Test.java
└── hibernate.cfg.xml

---
Fonctionnalités Implémentées
---
Gestion des Personnes
---
✅ Création d'hommes et de femmes

✅ Affichage de toutes les femmes

✅ Recherche de la femme la plus âgé

---
Gestion des Mariages
---
✅ Création de mariages avec dates début/fin

✅ Distinction mariages en cours / échoués

✅ Gestion du nombre d'enfants par mariage

---

Requêtes Spécifiques
---
✅ Épouses d'un homme entre deux dates

✅ Nombre d'enfants d'une femme entre deux dates

✅ Femmes mariées deux fois ou plus (requête nommée)

✅ Hommes mariés à 4 femmes entre deux dates (Criteria API)

✅ Détails complets des mariages d'un homme

---
Auteur
---
AIT LECHGUEUR BASMA
---
























