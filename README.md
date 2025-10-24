<h4 style="text-align:center; font-size:28px; margin-bottom:10px;color:#989890;">
Conception et démarche du projet </h4>
<h2 style="text-align:center; font-size:28px; margin-bottom:10px;color:#989890;">
1.Architecture de l’application </h2>


![Page de préchargement](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/38eeb0a541080286c5441299cb2f11c8dbb21868/web%20site1/Page%20de%20pr%C3%A9chargement%20.png)
*Lorsqu’on développe une application,
il est essentiel d’organiser son architecture en couches distinctes afin 
de mieux structurer le code et de garantir une maintenance plus facile. 
Ce principe, bien qu’antérieur à la programmation orientée objet, 
reste une référence dans la conception des applications modernes.
Dans le cadre de notre projet de gestion de stock, nous avons adopté une architecture en trois couches, chacune ayant un rôle bien défini :
  1.La couche présentation : Il s’agit de l’interface utilisateur qui permet aux gestionnaires de stock d’interagir avec l’application. Elle affiche les informations sur les produits, les quantités disponibles et facilite les opérations comme l’ajout ou la mise à jour des stocks.
    2.La couche logique métier : C’est le cœur de l’application. Elle contient toutes les règles de gestion liées au stock, comme le contrôle des seuils d’alerte, la gestion des entrées et sorties de produits, ou encore la validation des opérations avant qu’elles ne soient enregistrées.
    3.La couche données : Elle gère l’accès à la base de données, stocke les informations sur les produits, les mouvements de stock et les fournisseurs. Elle assure également l’intégrité et la cohérence des données.*


<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
2.MVC Modèle </h2>

![Page d'accueil ](
https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/b9adb84a1533bc3ef05b0a295c0c41634fd1f92a/web%20site1/Page%20d'accueil%20.jpg)
*Pour structurer notre application de gestion de stock, nous avons adopté le modèle MVC (Modèle-Vue-Contrôleur), qui permet de séparer les différentes responsabilités du système et de garantir une meilleure maintenabilité.
1. Modèle (Model) – Gestion des données
Le Modèle représente la logique métier et l’accès aux données. Dans notre projet, cette couche est implémentée côté Spring Boot, où nous avons défini :
Les entités JPA qui correspondent aux tables de la base de données (exemple : Produit, Fournisseur).
Les services qui appliquent la logique métier (exemple : mise à jour des stocks, gestion des Produit).
Les repositories qui permettent d’interagir avec la base de données via Spring Data JPA.
2. Vue (View) – Interface utilisateur avec Angular
   La Vue est la partie qui gère l’affichage et l’interaction avec l’utilisateur. Pour notre projet, nous avons utilisé Angular, qui permet de créer une interface dynamique et réactive.
Les composants Angular affichent les données récupérées depuis l’API et permettent aux utilisateurs d’interagir avec l’application (ajouter un produit, modifier le stock…).
L’utilisation de services Angular (HttpClient) permet de communiquer avec le Back-End Spring Boot pour récupérer ou envoyer des données.
3. Contrôleur (Controller) – Communication entre Vue et Modèle
Le Contrôleur joue le rôle d’intermédiaire entre le Modèle (données) et la Vue (interface utilisateur). Dans notre projet, les contrôleurs Spring Boot exposent des API REST qui sont consommées par l’application Angular.

*



<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
3.Le diagramme de classes (Class Diagram) </h2>

![Menu de Navigation ](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Menu%20de%20Navigation%20.png)






<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
3.Le diagramme de classes (Class Diagram)</h2>

![3.Le diagramme de classes (Class Diagram) ](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20A%20propos.jpg)
*Il représente la structure statique du système en définissant les classes principales (comme les produits, les fourniseur), leurs attributs et leurs relations. Cela aide à organiser et structurer les données manipulées par le système.
Les principales classes dans ce diagramme incluent EntreeStock, SortieStock, Produit, Magasin, Fournisseur, et CategorieProduit. Chaque classe possède des attributs spécifiques qui permettent de représenter les données essentielles dans le système. Par exemple, la classe EntreeStock contient des informations sur l'entrée des produits dans les magasins, incluant des détails comme le fournisseur, la date d’entrée, et les produits associés. La classe Produit représente les produits eux-mêmes, avec des informations sur leur prix, catégorie et description.
*




<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
2.Le diagramme de cas d'utilisation (Use Case Diagram) :</h2>

![Page Nos services](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20services.jpg)
*Il illustre les interactions entre les utilisateurs (comme les responsables de stock, les fournisseurs, les administrateurs) et le système. Ce diagramme aide à définir les différents scénarios d’utilisation du système pour gérer les stocks, effectuer des commandes et suivre les mouvements des produits.
 1.Acteur (Actor)       Administrateur
L'administrateur est l'utilisateur qui possède tous les droits pour gérer le système. Il peut effectuer toutes les opérations liées à la gestion des stocks, notamment l'ajout et la suppression de produits, la gestion des fournisseurs, l'affichage des rapports et le téléchargement des données sous différents formats.
 2.Cas d'utilisation principaux (Use Cases)      S’inscrire
L'administrateur peut s'inscrire pour créer un compte.
      Se connecter
Avant d'effectuer toute action, l'administrateur doit se connecter au système.
Cette opération est liée au cas "Vérification de l'authentification", qui garantit que l'utilisateur est autorisé à accéder au système.
     Visualisation du Dashboard
Permet à l'administrateur de visualiser des données statistiques sur le stock et les ventes via un tableau de bord interactif.
Ce tableau de bord affiche les indicateurs de performance, le stock disponible, les produits les plus vendus et l’état du stock dans chaque magasin.
      Gestion des Entrées de Stock
L'administrateur peut ajouter de nouvelles quantités de produits au stock.
Il peut aussi définir le fournisseur, la quantité, la date d’entrée et l’emplacement du produit dans l’entrepôt.
Opérations disponibles :
Ajouter un nouveau produit au stock
Modifier les informations d’une entrée
Supprimer une entrée de stock
Télécharger un rapport en PDF ou Excel

 *




<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
3.Le diagramme de séquence (Sequence Diagram) :</h2>

*Il illustre l'interaction entre les objets du système selon un ordre chronologique. Ce diagramme aide à comprendre comment les messages sont échangés entre les différents objets (comme les utilisateurs et le système) et comment les opérations sont exécutées étape par étape dans le système.*
<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
1.Diagramme de séquence de l'authentification
</h2>
![3.Le diagramme de séquence (Sequence Diagram) : ](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20r%C3%A9alisations.jpg)
*1-Saisie des identifiants
L’Admin saisit son nom d’utilisateur et mot de passe dans l’application.
L’application envoie ces informations pour authentification.
2-Vérification des identifiants avec Spring Security
Spring Security intercepte la requête grâce à un filtre d’authentification.
Il déchiffre et vérifie les informations d’identification.
3-Cas où l’authentification échoue

Condition : Si le nom d’utilisateur ou le mot de passe est incorrect.
Spring Security rejette la demande d’authentification.
L’application retourne une erreur 401 (Unauthorized) pour indiquer que l’authentification a échoué.
L’utilisateur ne peut pas accéder aux ressources protégées.
4-Vérification du rôle de l’utilisateur

Condition : Si les identifiants sont corrects.
L’application vérifie le rôle de l’utilisateur (dans ce cas, "Admin").
Elle s’assure que l’utilisateur a les permissions requises pour accéder aux ressources demandées.

5-Cas où l’utilisateur n’a pas les permissions requises

Condition : Si le rôle de l’utilisateur n’est pas autorisé à accéder à la ressource.
L’application retourne une erreur 403 (Forbidden) pour indiquer que l’utilisateur est authentifié, mais n’a pas l’autorisation d’accéder à la ressource demandée.

6-Génération et envoi du Token JWT

Condition : Si l’authentification et l’autorisation réussissent.
L’application génère un Token JWT pour l’utilisateur.
Ce Token JWT est un jeton de session sécurisé, qui contient des informations sur l’utilisateur et son rôle.
Il est envoyé au client pour être utilisé lors des prochaines requêtes.
7-Redirection et accès aux ressources

L’application redirige l’utilisateur vers l’interface appropriée.
L’utilisateur peut maintenant accéder aux ressources protégées en envoyant son Token JWT dans les requêtes futures (Authorization: Bearer [Token]).

*

<h2 style="text-align:center; font-size:28px; margin-bottom:10px;">
2.Diagramme de Séquence : Création d’une Entrée de Stock
</h2>
![2.Diagramme de Séquence : Création d’une Entrée de Stock](https://github.com/RabiaMAHIR/WebSiteBETOCONCEPT/blob/main/web%20site1/Page%20Nos%20r%C3%A9alisations.jpg)
* 1.Les Acteurs Impliqués:ADMIN : C’est l’utilisateur (gestionnaire ou administrateur) qui saisit les informations des produits entrant en stock.
UI (Interface Utilisateur) : L’interface qui facilite l’interaction entre l’utilisateur et la base de données.
BD (Base de Données) : L’endroit où sont stockées toutes les informations sur les produits et les quantités disponibles.
 2.Déroulement du Processuss:Saisie des informations : Lorsqu'un nouvel arrivage de produits est enregistré, l’utilisateur renseigne les détails de l’entrée de stock (nom du produit, quantité, référence, etc.).
Vérification des erreurs : Si les informations saisies sont incorrectes ou incomplètes, un message d’erreur est affiché pour éviter toute incohérence.
Envoi des données : Une fois les informations validées, elles sont transmises à la base de données via l’interface utilisateur.
Vérification de l'existence du produit :
                 -Si le produit existe déjà dans la base de données, sa quantité est mise à jour    en ajoutant la nouvelle entrée.
             -Si le produit    est nouveau, une nouvelle fiche est créée dans la base de données.
Enregistrement des modifications : Le système applique les changements (mise à jour ou ajout d’un nouvel article).
Confirmation de l’opération : Une réponse est envoyée pour indiquer si l’opération a été effectuée avec succès.
Affichage du message final : L’interface utilisateur affiche un message confirmant le succès ou l’échec de l’opération.
3.Importance de ce Processus:Il garantit une mise à jour fiable des stocks en temps réel.
Il empêche les erreurs de saisie grâce à la validation des informations.
Il optimise la gestion des stocks en évitant les doublons et en maintenant des données précises.
Ce diagramme de séquence joue un rôle fondamental dans mon projet, car il permet d’automatiser et de sécuriser l’enregistrement des entrées en stock, assurant ainsi une gestion efficace et structurée des produits.*
