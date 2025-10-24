# 🧩 Conception et démarche du projet

## 📘 Introduction

Dans le cadre de notre projet de **gestion de stock**, nous avons conçu une application web complète basée sur une architecture solide et moderne.  
L’objectif principal de cette section est de présenter les choix techniques et méthodologiques qui ont guidé la conception du projet, notamment l’architecture logicielle, le modèle MVC, ainsi que les principaux diagrammes UML (classes, cas d’utilisation et séquences).  
Ces éléments permettent de mieux comprendre la structure du système et les interactions entre ses différentes composantes.

---

## 🏗️ 1. Architecture de l’application

![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

Lorsqu’on développe une application, il est essentiel d’organiser son architecture en couches distinctes afin de mieux structurer le code et de garantir une maintenance plus facile.  
Ce principe, bien qu’antérieur à la programmation orientée objet, reste une référence dans la conception des applications modernes.

Dans le cadre de notre projet de gestion de stock, nous avons adopté une **architecture en trois couches**, chacune ayant un rôle bien défini :

1. **Couche Présentation**  
   Il s’agit de l’interface utilisateur qui permet aux gestionnaires de stock d’interagir avec l’application.  
   Elle affiche les informations sur les produits, les quantités disponibles et facilite les opérations comme l’ajout ou la mise à jour des stocks.

2. **Couche Logique Métier**  
   C’est le cœur de l’application. Elle contient toutes les règles de gestion liées au stock, comme le contrôle des seuils d’alerte, la gestion des entrées et sorties de produits, ou encore la validation des opérations avant qu’elles ne soient enregistrées.

3. **Couche Données**  
   Elle gère l’accès à la base de données, stocke les informations sur les produits, les mouvements de stock et les fournisseurs.  
   Elle assure également l’intégrité et la cohérence des données.

---

## 🧱 2. Modèle MVC (Model - View - Controller)

![Modèle MVC](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/mvc.webp)

Pour structurer notre application, nous avons adopté le **modèle MVC**, qui permet de séparer les différentes responsabilités du système et d’assurer une meilleure maintenabilité.

### 🔹 1. Modèle (Model) – Gestion des données
Le **Modèle** représente la logique métier et l’accès aux données.  
Dans notre projet, cette couche est implémentée côté **Spring Boot**, où nous avons défini :
- Les **entités JPA** correspondant aux tables de la base de données (ex : Produit, Fournisseur)  
- Les **services** qui appliquent la logique métier (ex : mise à jour des stocks)  
- Les **repositories** qui interagissent avec la base via Spring Data JPA  

### 🔹 2. Vue (View) – Interface utilisateur avec Angular
La **Vue** gère l’affichage et l’interaction avec l’utilisateur.  
Nous avons utilisé **Angular** pour créer une interface dynamique et réactive :
- Les composants Angular affichent les données récupérées depuis l’API  
- Les services Angular (HttpClient) communiquent avec le Back-End Spring Boot  

### 🔹 3. Contrôleur (Controller) – Communication entre Vue et Modèle
Le **Contrôleur** joue le rôle d’intermédiaire entre le modèle et la vue.  
Dans notre projet, les contrôleurs **Spring Boot** exposent des **API REST** consommées par l’application Angular.

---

## 🧩 3. Diagramme de classes (Class Diagram)

![Diagramme de classes](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Menu%20de%20Navigation%20.png)

![Diagramme de classes](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/Le%20diagramme%20de%20classes%20(Class%20Diagram)%20.png)

Le **diagramme de classes** représente la structure statique du système en définissant les classes principales (comme les produits, les fournisseurs), leurs attributs et leurs relations.  

Les principales classes incluent :
- `EntreeStock`
- `SortieStock`
- `Produit`
- `Magasin`
- `Fournisseur`
- `CategorieProduit`

Chaque classe possède des attributs spécifiques qui permettent de représenter les données essentielles.  
Exemples :
- `EntreeStock` → informations sur les entrées de produits (fournisseur, date d’entrée, produits associés)  
- `Produit` → informations sur le prix, la catégorie et la description  

---

## 🧠 4. Diagramme de cas d’utilisation (Use Case Diagram)

![Diagramme de cas d’utilisation](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/Le%20diagramme%20de%20cas%20d'utilisation%20(Use%20Case%20Diagram)%20.png)

Ce diagramme illustre les interactions entre les utilisateurs (administrateurs, responsables de stock, fournisseurs) et le système.

### 👤 Acteur principal : Administrateur
L’administrateur possède tous les droits pour gérer le système :
- Ajout et suppression de produits  
- Gestion des fournisseurs  
- Affichage de rapports  
- Téléchargement de données (PDF / Excel)

### ⚙️ Cas d’utilisation principaux :
1. **S’inscrire** – Création d’un compte administrateur  
2. **Se connecter** – Vérification de l’authentification  
3. **Visualisation du Dashboard** – Accès aux statistiques et indicateurs clés  
4. **Gestion des Entrées de Stock** – Ajouter, modifier, supprimer ou télécharger les rapports

---

## 🔄 5. Diagramme de séquence (Sequence Diagram)

Le **diagramme de séquence** illustre l’interaction entre les objets du système selon un ordre chronologique.  
Il permet de comprendre comment les messages sont échangés et comment les opérations sont exécutées étape par étape.

### 🔐 1. Diagramme de séquence de l’authentification

![Diagramme de séquence](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/Diagramme%20de%20s%C3%A9quence%20de%20l'authentification.jpg)

#### Étapes :
1. **Saisie des identifiants** – L’admin entre ses informations.  
2. **Vérification Spring Security** – Analyse et décryptage des identifiants.  
3. **Erreur 401** – Si l’utilisateur ou le mot de passe est incorrect.  
4. **Vérification du rôle** – Validation du rôle "Admin".  
5. **Erreur 403** – Si l’utilisateur n’a pas les permissions requises.  
6. **Génération du Token JWT** – Création d’un jeton sécurisé.  
7. **Redirection** – Accès autorisé aux ressources protégées.

---

### 📦 2. Diagramme de Séquence : Création d’une Entrée de Stock

![Création d’une Entrée de Stock](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/Diagramme%20de%20S%C3%A9quence.png)

#### Acteurs impliqués :
- **ADMIN** : saisit les informations d’entrée.  
- **UI (Interface Utilisateur)** : communique avec la base de données.  
- **BD (Base de Données)** : stocke les informations sur les produits.  

#### Déroulement du processus :
1. Saisie des informations (nom du produit, quantité, etc.)  
2. Vérification des erreurs et validation  
3. Envoi des données vers la base  
4. Vérification si le produit existe déjà  
5. Mise à jour ou création d’une nouvelle fiche  
6. Enregistrement des modifications  
7. Message de confirmation ou d’échec  

#### Importance :
- Mise à jour fiable des stocks en temps réel  
- Prévention des erreurs de saisie  
- Gestion optimisée et cohérente des données  

---

✨ *Ce diagramme de séquence joue un rôle fondamental dans notre projet, car il automatise et sécurise la gestion des entrées de stock, garantissant une administration fluide et structurée du système.*
