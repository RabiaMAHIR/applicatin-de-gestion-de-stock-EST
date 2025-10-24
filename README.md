<h1 style="text-align:center; font-size:32px; color:#6c757d;">
Application de Gestion de Stock – EST Taroudant
</h1>

<h3 style="text-align:center; color:#a0a0a0;">
Projet réalisé dans le cadre de l’École Nationale Supérieure de l’Intelligence Artificielle et des Sciences des Données – Taroudant
</h3>

---

## 🎯 Introduction

L’application de **gestion de stock** développée vise à faciliter la gestion des produits, des fournisseurs et des mouvements d’entrée et de sortie au sein de l’établissement.  
Elle permet de centraliser les informations, d’éviter les erreurs humaines et d’assurer une meilleure visibilité sur les quantités disponibles.  

Ce projet a été conçu selon une approche moderne combinant :
- **Back-End** avec *Spring Boot*
- **Front-End** avec *Angular*
- **Base de données** relationnelle (ex. MySQL)
- **Architecture en couches (3-tier architecture)**
- **Modèle MVC (Modèle–Vue–Contrôleur)**  

---

<h4 style="text-align:center; font-size:28px; margin-bottom:10px; color:#989890;">
Conception et démarche du projet
</h4>

<h2 style="text-align:center; font-size:28px; margin-bottom:10px; color:#989890;">
1. Architecture de l’application
</h2>

![Page de préchargement](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/38eeb0a541080286c5441299cb2f11c8dbb21868/web%20site1/Page%20de%20pr%C3%A9chargement%20.png)

Lorsqu’on développe une application, il est essentiel d’organiser son architecture en couches distinctes afin 
de mieux structurer le code et de garantir une maintenance plus facile.  
Ce principe, bien qu’antérieur à la programmation orientée objet, reste une référence dans la conception des applications modernes.

Dans le cadre de notre projet de gestion de stock, nous avons adopté une **architecture en trois couches**, chacune ayant un rôle bien défini :

1️⃣ **Couche Présentation :**  
Interface utilisateur permettant aux gestionnaires d’interagir avec le système.  
Elle affiche les produits, les quantités disponibles, et permet l’ajout ou la mise à jour des stocks.

2️⃣ **Couche Logique Métier :**  
C’est le cœur du système.  
Elle contient les règles de gestion (contrôle des seuils d’alerte, entrées/sorties de produits, validation des opérations…).

3️⃣ **Couche Données :**  
Responsable de la gestion de la base de données.  
Elle stocke les informations sur les produits, les mouvements de stock et les fournisseurs, assurant cohérence et intégrité.

---

<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
2. Modèle MVC
</h2>

![Page d'accueil](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/b9adb84a1533bc3ef05b0a295c0c41634fd1f92a/web%20site1/Page%20d'accueil%20.jpg)

Pour structurer notre application, nous avons adopté le **modèle MVC (Modèle-Vue-Contrôleur)** afin de séparer clairement les responsabilités et garantir la maintenabilité du code.

- **Modèle (Model)** – Gestion des données :  
  Géré côté *Spring Boot* avec les entités JPA, services métier et repositories pour l’accès à la base de données.

- **Vue (View)** – Interface utilisateur :  
  Développée en *Angular*, elle gère l’affichage et les interactions avec l’utilisateur grâce aux composants dynamiques et réactifs.

- **Contrôleur (Controller)** – Communication entre la Vue et le Modèle :  
  Géré par *Spring Boot*, il expose des **API REST** consommées par l’application *Angular* via le module `HttpClient`.

---

<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
3. Diagramme de classes (Class Diagram)
</h2>

![Diagramme de classes](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20A%20propos.jpg)

Le **diagramme de classes** représente la structure statique du système.  
Il définit les classes principales (Produit, Fournisseur, EntréeStock, SortieStock, CatégorieProduit, Magasin) et leurs relations.

Exemples :
- **Produit** : contient le nom, la description, le prix, la catégorie.  
- **EntréeStock** : informations sur la date d’entrée, le fournisseur et la quantité ajoutée.  
- **Fournisseur** : détaille les partenaires qui approvisionnent le stock.

Ce diagramme facilite la compréhension et la structuration des données manipulées par le système.

---

<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
4. Diagramme de cas d'utilisation (Use Case Diagram)
</h2>

![Page Nos services](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20services.jpg)

Ce diagramme illustre les **interactions entre les utilisateurs et le système**.  
Les acteurs principaux sont :
- **Administrateur** : gère l’ensemble du système.
- **Responsable de stock** : enregistre les mouvements de produits.
- **Fournisseur** : approvisionne le stock.

**Cas d’utilisation principaux :**
- S’inscrire et se connecter (authentification sécurisée)
- Visualiser le tableau de bord (Dashboard)
- Gérer les entrées et sorties de stock
- Générer des rapports PDF/Excel

---

<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
5. Diagrammes de séquence (Sequence Diagram)
</h2>

### 🔐 Diagramme de séquence : Authentification

![Sequence Diagram Auth](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20r%C3%A9alisations.jpg)

Ce diagramme décrit les étapes du processus d’authentification :
1. L’administrateur saisit ses identifiants.  
2. *Spring Security* vérifie et déchiffre les données.  
3. En cas d’erreur : rejet (erreur 401).  
4. Vérification du rôle de l’utilisateur.  
5. Si non autorisé : erreur 403.  
6. Si valide : génération du **token JWT**.  
7. Redirection vers l’interface protégée.

---

### 📦 Diagramme de séquence : Création d’une entrée de stock

![Sequence Diagram Entrée Stock](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20r%C3%A9alisations.jpg)

**Étapes du processus :**
1. L’administrateur saisit les détails d’un nouvel arrivage.  
2. L’interface valide les données saisies.  
3. Si le produit existe déjà → mise à jour.  
4. Sinon → création d’un nouveau produit.  
5. Les modifications sont enregistrées dans la base de données.  
6. L’application confirme la réussite de l’opération.  

**Importance :**  
✔️ Mise à jour fiable en temps réel  
✔️ Réduction des erreurs de saisie  
✔️ Gestion optimale et structurée des stocks

---

<h3 style="text-align:center; font-size:24px; color:#6c757d;">
✨ Ce projet illustre l’application concrète des concepts de génie logiciel, d’architecture logicielle et d’intégration entre technologies modernes (Angular & Spring Boot).
</h3>
