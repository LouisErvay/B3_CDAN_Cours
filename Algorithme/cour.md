# 2025/11/20 - Premier cour : Algorithme Intermédiaire

Cour du prof :  
https://ipi.aezi.fr/b3dev-25-26/programmation/complexite.html

## La compléxité algorithmique

La complexité algorithmique correspond au nombre d'opérations effectuées par l'algorithme.  
Elle se calcule en fonction de la taille des données en entrée et de la durée d'exécution de l'algorithme.

Il y à deux types de complexité :

-   La complexité temporelle : correspond au nombre d'opérations effectuées par l'algorithme.
-   La complexité spatiale : correspond à l'espace mémoire utilisé par l'algorithme.

On note la complexité algorithmique de la manière suivante :

-   **O(1)** : complexité temporelle **constante** (elle ne dépend pas de n)
-   **O(n)** : complexité temporelle **linéaire**
-   **O(n^2)** : complexité temporelle **quadratique**
-   **O(n^3)** : complexité temporelle **cubique**
-   **O(log(n))** : complexité temporelle **logarithmique**
-   **O(n!)** : complexité temporelle **factorielle**

### Exercice de calcul de complexité selon les types de données JAVA

Pour chaque cas :
Calculer les complexités temporelles (pire cas).
Calculer les complexités temporelles (cas moyen).
Calculer la complexité spatiale.

---

-   **Tableau (Array)** :

    -   Complexité temporelle (pire cas) : O(n)

        -   Lors d'une insertion ou suppression au milieu, tous les éléments suivants doivent être décalés.

    -   Complexité temporelle (cas moyen) : O(1)

        -   L'accès à un élément par son index se fait en temps constant.

    -   Complexité spatiale : O(n)
        -   L’espace nécessaire est proportionnel au nombre d’éléments stockés.

---

-   **Pile (Stack)** :

    -   Complexité temporelle (pire cas) : O(1)

        -   Les opérations push et pop s’effectuent toujours sur le sommet.

    -   Complexité temporelle (cas moyen) : O(1)

        -   Même justification : aucune traversée ou réorganisation n'est nécessaire.

    -   Complexité spatiale : O(n)

        -   La pile stocke n éléments, donc son espace croît linéairement.

---

-   **File (Queue)** :

    -   Complexité temporelle (pire cas) : O(1)

        -   Les opérations enqueue et dequeue se font aux extrémités en temps constant.

    -   Complexité temporelle (cas moyen) : O(1)

        -   Comme pour le pire cas, aucune opération ne nécessite de déplacement global.

    -   Complexité spatiale : O(n)

        -   L’espace dépend directement du nombre d’éléments stockés.

---

-   **Liste simplement chaînée (LinkedList)** :

    -   Complexité temporelle (pire cas) : O(n)

        -   L’accès ou la recherche nécessite de parcourir potentiellement tous les nœuds.

    -   Complexité temporelle (cas moyen) : O(n)

        -   En moyenne, il faut parcourir la moitié de la liste pour atteindre un élément.

    -   Complexité spatiale : O(n)

        -   Chaque élément nécessite un nœud, donc l’espace est linéaire.

---

-   **Liste doublement chaînée (DoublyLinkedList)** :

    -   Complexité temporelle (pire cas) : O(n)

        -   Parcourir la liste pour accéder ou rechercher un élément prend du temps linéaire.

    -   Complexité temporelle (cas moyen) : O(n)

        -   En moyenne, la recherche parcourt n/2 éléments.

    -   Complexité spatiale : O(n)

        -   Deux pointeurs par nœud (précédent et suivant), mais toujours proportionnel à n.

---

-   **Hash Table** :

    -   Complexité temporelle (pire cas) : O(n)

        -   En cas de collisions extrêmes, toutes les clés se retrouvent dans le même bucket.

    -   Complexité temporelle (cas moyen) : O(1)

        -   L’accès, l’insertion ou la recherche se basent sur un calcul de hash constant.

    -   Complexité spatiale : O(n)

        -   La table stocke n éléments plus les buckets, restant linéaire.

---

-   **Arbre binaire de recherche (Binary Search Tree)** :

    -   Complexité temporelle (pire cas) : O(n)

        -   L’arbre peut se dégénérer en liste si les valeurs arrivent triées.

    -   Complexité temporelle (cas moyen) : O(log(n))

        -   La hauteur moyenne est logarithmique, permettant une recherche efficace.

    -   Complexité spatiale : O(n)

        -   Un nœud par élément, donc espace linéaire.
