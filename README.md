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

![Création d’une Entrée de Stock](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/Diagramme%20de%20S%C3%A9quence2.png)

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






# 🧩 Interfaces Graphiques
## 📘 Introduction

Les interfaces graphiques de notre application de gestion de stock ont été conçues pour faciliter la navigation et la gestion des produits de manière intuitive.
Chaque page a été pensée pour permettre à l’utilisateur d’ajouter, de modifier ou de supprimer des articles, tout en visualisant l’état du stock en temps réel grâce à une présentation claire et ergonomique.




---

## 🏗️  1.Page de connexion  

![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

Lorsqu’on développe une application, il est essentiel d’organiser son architecture en couches distinctes afin de mieux structurer le code et de garantir une maintenance plus facile.  
Ce principe, bien qu’antérieur à la programmation orientée objet, reste une référence dans la conception des applications modernes.

Dans le cadre de notre projet de gestion de stock, nous avons adopté une **architecture en trois couches**, chacune ayant un rôle bien défini :

1. **1.Saisie des informations de connexion**  
L’utilisateur tente de se connecter en entrant son nom d’utilisateur et son mot de passe dans l’interface (Front-End).


3. * 2.Envoi de la requête de connexion à l’API**  
   Lorsque l’utilisateur clique sur "Se Connecter", une requête HTTP POST est envoyée à l’API via Angular. Nous pouvons observer cette requête dans l’onglet Network des outils de développement (DevTools).

![Envoi de la requête de connexion](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

4. **3.Génération et envoi du JWT à l’utilisateur**  
  Si les identifiants sont valides, le serveur génère un JWT Token contenant les informations utilisateur :
Ce token est signé avec une clé secrète (SECRET_KEY) puis envoyé au client dans l’en-tête de la réponse
Ce token est utilisé pour authentifier l’utilisateur dans les requêtes futures.
---
## 🏗️  2.Page de création de compte  
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

Lorsqu'un utilisateur accède à la page de création de compte, il est invité à remplir plusieurs champs, notamment son prénom, nom, email, numéro de téléphone, nom d'utilisateur et mot de passe. Après avoir saisi ces informations, il clique sur le bouton d'inscription pour valider son compte. Si toutes les informations sont correctes, il est redirigé vers l’interface principale 

## 🏗️   3.Menu : 
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

Dashboard : Page principale qui affiche un résumé des statistiques du stock (entrées, sorties, fournisseurs, magasins, etc.).
Sortie Management : Gestion des sorties de stock (retrait de produits).
Entree Management : Gestion des entrées de stock
Agent Management : Gestion des utilisateurs ou employés qui ont accès au système.
Produit Management : Gestion des produits stockés.
Store Management : Gestion des magasins ou entrepôts où sont stockés les produits.
Security Management : Gestion des aspects de sécurité, comme les autorisations d'accès.

##  4. Top bar :
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

 Bouton Afficher le menu (MENU) :Lorsqu'il est cliqué, le menu latéral apparaît à l'écran avec les différentes sections. Lorsqu'il est cliqué à nouveau, le menu se ferme, offrant ainsi plus d'espace pour le contenu de la page.
 Bouton Déconnexion (Logout):En cliquant dessus, la session en cours est fermée et l'utilisateur est redirigé vers HOME pour démarrer une nouvelle session si nécessaire.
  Bouton Retour à la page d'accueil (Home): Lorsqu'il est cliqué, l'utilisateur est directement dirigé vers la page d'accueil
  Bouton Changer la langue (Language Switch):Lorsqu'il est cliqué, une liste des langues disponibles apparaît, permettant à l'utilisateur de choisir celle qu'il préfère pour changer l'interface de l'application.


  ##   5.Dashboard  :
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

 1.Cartes d'affichage principales (Cards)
 a. Total des entrées de stock (ENTREE STOCK)
Affiche le montant total des produits entrés dans les magasin durant l’année en cours.
Le pourcentage de variation est calculé par rapport à l’année précédente, permettant ainsi d’évaluer l’augmentation ou la diminution des entrées.

b. Nombre total de fournisseurs (FOURNISSEUR)
Affiche le nombre total de fournisseurs enregistrés qui approvisionnent les entrepôts.
Cette donnée permet de suivre le nombre de fournisseurs actifs et de gérer efficacement les relations d’approvisionnement.
c. Nombre total d’entrepôts (MAGASIN)
Indique le nombre total d’entrepôts enregistrés dans le système.
Cette information est essentielle pour évaluer la répartition des produits et leur gestion à travers différents sites de stockage.
2. Graphiques 
a. Graphique en courbes et en zones (Line & Area Chart)
Montre l’évolution des quantités de produits entrés (ENTREE STOCK) et sortis (SORTIE STOCK) au fil des mois.
La ligne bleue représente les entrées de stock, tandis que la ligne rouge correspond aux sorties.
L’utilisateur peut sélectionner une année spécifique pour mettre à jour les données et observer les variations.
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

b. Graphique à barres (Bar Chart)
Illustre les variations des montants des entrées et sorties de stock mois par mois pour une année donnée.
Par défaut, les données affichées concernent l’année en cours, mais l’utilisateur peut choisir une autre année.
La barre bleue représente les entrées de stock, tandis que la barre verte symbolise les sorties.
c. Graphique circulaire (Pie Chart)
Affiche, en mode standard, la répartition en pourcentage des différents produits stockés dans tous les magasin .
Lorsqu’un magasin spécifiqueest sélectionné, le graphique montre uniquement la répartition des produits dans cet entrepôt.
Chaque produit est représenté par une couleur distincte pour une meilleure lisibilité.
Ce graphique aide à identifier les produits dominants dans le stock et ceux qui sont moins présents.
3. Mise à jour et analyse des données
Par défaut, tous les graphiques affichent les données de l’année en cours.
Lorsqu’une année spécifique est sélectionnée, les données sont mises à jour pour offrir une analyse plus précise.
L’utilisation de différentes couleurs permet de mieux différencier les informations et de visualiser clairement les écarts.

##   6. Page ajout une entrée de stock: :
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)

 Saisie des informations de base
Dans cette première phase, les informations essentielles à l’identification de l’opération sont saisies :Date d’entrée (Date entrée) : La date effective de l’ajout du stock est sélectionnée.
Code de l’opération (Code) : Un code unique est attribué pour identifier l’entrée dans le système.
Description (Description) : Des remarques ou des détails supplémentaires peuvent être ajoutés si nécessaire.


![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)
ne fois les informations de base validées, les détails du produit stocké sont enregistrés, incluant :
Produit (Produit) : Le produit est sélectionné dans la liste déroulante.
Prix (Prix) : Le prix unitaire du produit est saisi.
Quantité (Quantité) : Le nombre d’unités à ajouter au stock est défini.
Magasin (Magazin) : L’emplacement où le produit sera stocké est choisi.
L’utilisateur valide les données en cliquant sur Valider pour passer à l’étape finale.
 Vérification de l’entrée dans la liste (Liste)
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)


 Après l’enregistrement des données, l’entrée est affichée dans la liste des stocks. Cette étape permet :
De vérifier l’exactitude des informations dans l’onglet Liste.
De modifier l’entrée si nécessaire.
De supprimer l’entrée en cas d’erreur.
Une fois les informations validées, l’utilisateur clique sur Valider pour finaliser l’opération.
la liste des sorties de stock 
 ![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)
Il existe dans la liste des sorties de stock des détails essentiels, notamment la date de sortie, le code et les actions disponibles (modification, suppression, consultation). L’utilisateur peut rechercher un enregistrement spécifique, trier les données et exporter la liste sous différents formats (CSV, XLS, PDF). Le bouton "New" permet d’ajouter une nouvelle sortie, tandis que le bouton "Delete" permet de supprimer les éléments sélectionnés.
la liste des sorties de stock 
 ![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)
 ![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)



##   6. Page ajout une entrée de stock: :
![Architecture de l’application](https://github.com/RabiaMAHIR/applicatin-de-gestion-de-stock-EST/blob/main/architecture.jpg?raw=true)
L'utilisateur peut choisir une méthode de calcul dans le menu déroulant. Selon la méthode sélectionnée, le coût total (totalCost) est recalculé. Une fois la méthode choisie, l’opération est validée en cliquant sur "Confirm".
