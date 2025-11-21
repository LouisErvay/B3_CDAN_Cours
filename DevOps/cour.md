# 2025/11/21 - Premier cour : DevOps Méthodes et Pilotage

## Outillages :

-   **Notion** : Plateforme de gestion de projet et de connaissances.

-   **Snyk** : Plateforme de gestion de vulnérabilités et de scan de codebase pour retrouver les failles de sécurité.

## la culture devops :

### Quatres pilliers :

-   **Culture** : Culture de la qualité, de la sécurité, de l'efficacité, de la collaboration.

-   **Méthodes** : Méthodes de développement, de gestion de projet, de gestion de la qualité, de la sécurité, de l'efficacité, de la collaboration.

-   **Pilotage** : Pilotage de la qualité, de la sécurité, de l'efficacité, de la collaboration.

-   **Outils** : Outils de développement, de gestion de projet, de gestion de la qualité, de la sécurité, de l'efficacité, de la collaboration.

### Les priorités agiles :

| Priorité                     | Over | Description                 |
| ---------------------------- | ---- | --------------------------- |
| Individuals and interactions | over | processes and tools         |
| Working software             | over | comprehensive documentation |
| Customer collaboration       | over | contract negotiation        |
| Responding to change         | over | following a plan            |

### Les 12 principes de la méthode agile :

-   **1. Satisfaction du client** par la livraison continue et précoce de logiciels de valeur
-   **2. Bienvenue des changements de besoins** même en fin de développement. Les processus agiles exploitent le changement pour le bénéfice de l'avantage concurrentiel du client
-   **3. Livraison de logiciels fonctionnels fréquemment** de quelques semaines à quelques mois, avec une préférence pour l'échelle de temps la plus courte
-   **4. Communication fréquente** entre les business people et les développeurs
-   **5. Communication face à face** est le moyen le plus efficace et efficace de transmettre des informations à et dans un équipe de développement
-   **6. Mesure de la progression** est le logiciel fonctionnel
-   **7. Process de développement** solide et durable est possible grace aux individus motivés et aux outils adaptés.
-   **8. Bon design** est essentiel pour l'agilité.
-   **9. Mesure du progrès** pour une bonne avancée du projet.
-   **10. Supporter les membres de la team** pour une bonne avancée du projet.
-   **11. Contrôle des résultats perpétuels** pour une bonne avancée du projet.
-   **12. Réflexion et remise en question du calendrier** pour une bonne avancée du projet.

### Comparaison de cultures :

| Pathologique                          | Bureaucratique                  | Générative                      |
| ------------------------------------- | ------------------------------- | ------------------------------- |
| Faible coopération                    | Coopération modeste             | Grande coopération              |
| Messagers "abattus"                   | Messagers négligés              | Messagers formés                |
| Responsabilité non assumées           | Responsabilités limitées        | Risques artagés                 |
| Liaison découragée                    | Liaison tolérée                 | Liaison encouragée              |
| L'échec conduit à se rejeter la faute | L'échec conduit au tribunal     | L'échec conduit à une enquête   |
| La nouveauté est écrasée              | La nouveauté crée des problèmes | La nouveauté est mise en oeuvre |

## DX / Developper Experience :

La developper experience correspond aux méthodes, outils et processus mis en place pour faciliter le travail des développeurs.  
Ce dernier varie selon chaque entreprises, et certaines ont des processus bien mis en places, la ou d'autres non.

### Quoi Automatiser ?

Etapes du cycle de vie du développement logiciel :

-   La construction
-   Le test
-   Le déploiement
-   L'exploitation des applications.

Et plus généralement, toutes les tâches complexes et répétitives (toil en anglais) peuvent/doivent être automatisées.

## CI / CD :

### CI / Continuous Integration :

Les nouveaux développements sont validés en continu.

**Pourquoi ?**

-   Feedback rapide aux développeurs en cas d'erreurs
-   Garantir la qualité du code
-   Faciliter le travail collaboratif

**Exemples**

-   Exécution de tests
-   Analyse de la qualité du code
-   Construction des livrables (RELEASE)

### Continuis TESTING :

**Pourquoi ?**

-   Accélérer la validation des éveloppements.
-   Améliorer la qualité de sproduits
-   Accélérer l'apport d elvaleur

**Types de tests ?**

-   Test rapides : tests unitaites, d'intégration
-   Test lents : tests manuels, de performance..

**Points clés :**

-   Garantir la qualité des tests via une collaboration entre développeurs, testers
-   Automatiser les tests

### Continuous Delivery :

Le Continuous Delivery est **Manuel** (cliquer moi même sur le bouton déployer).

**Pourquoi ?**

-   Livrer fréquemment et en en petits lots.
-   Garder la maitrise sur les releases.

### Continuous Deployment :

Le Continuous Deployment est **Automatique**.

**Comment ?**

-   Extension du Continuous Delivery
-   Déploiment auto de la nouvelle version

**Points clés :**

-   Ouvrir progressivement la nouvelle version
-   Superviser les déploiments (Continuous Monitoring)
    -   Retour en arrière automatique en cas de problème

### Canary deployment :

C'est le principe du déploiement progressif à une portion des utilisateurs.

-   Permet d'ouvrir progressivement la nouvelle version à une portion des utilisateurs.
    -   10% pendant 1 jour
    -   20% pendant 2 jours
    -   ...
-   Tester & monitorer la version
    -   Si anomalies, revenir en arrière

## Supervision et Observabilité :

### Supervision :

La supervision est le processus de surveillance et de contrôle des systèmes.

**Obtenir les informations**

-   Sur les services (temps de réponse, disponibilité, état de santé)
-   Sur les ressources (CPU, mémoire, disque)

### Observabilité :

L'observabilité est la capacité à observer le système et de comprendre son fonctionnement.

**Les 3 V**

-   Visibilité :
    -   On voit ce qu'il se passe.
-   Verifiabilité :
    -   On peut vérifier ce qu'il se passe.
-   Verification :
    -   On peut vérifier si ce qu'il se passe est correct.

### Tracing :

Le tracing est la modélisation des graphes d'appels de services distribués.

**Vocabulaire :**

-   Trace : Trajet de bout en bout d'un requete à travers un système distribué.
-   Span : Appel à un service.

### Niveau de service :

-   SLI : **Service Level Indicator**

    -   Mesure un aspect du niveau de service.

-   SLO : **Service Level Objective**

    -   Objectif que se fixe le fournisseur sur l'indicateur

-   SLA : **Service Level Agreement**

    -   Accord sur l'indicateur entre le client et le fournisseur.

## Performance DevOps :

### DORA Devops Research & Assessment :

-   Deploy Frequency : Fréquence de déploiement.

-   Lead Time : Temps moyen entre la création du ticket et le déploiement.

-   Change Failure Rate : Taux de faillite des changements.

-   Mean Time to Restore : Temps moyen de réparation d'un incident.
