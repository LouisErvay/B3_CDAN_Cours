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

    ```python
    def add(a, b):                  # ne dépend pas des valeurs de a et b
        return a + b
    ```

-   **O(n)** : complexité temporelle **linéaire**

    ```python
    for i in range(n):              # dépend de la taille de n
        print(i)
    ```

-   **O(n^2)** : complexité temporelle **quadratique**

    ```python
    for i in range(n):              # dépend de la taille de n au carré
        for j in range(n):
            print(i, j)
    ```

-   **O(n^3)** : complexité temporelle **cubique**

    ```python
    def print_triplets(n):          # exécuté n³ fois
    for i in range(n):
        for j in range(n):
            for k in range(n):
                print(i, j, k)
    ```

-   **O(log(n))** : complexité temporelle **logarithmique**

    ```python
    def binary_search(arr, target): # on divise le problème par 2 à chaque étape
    left, right = 0, len(arr) - 1

    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1

    return -1
    ```

-   **O(n!)** : complexité temporelle **factorielle**

    ```python
    import itertools            # n! permutations

    def print_permutations(arr):
        for perm in itertools.permutations(arr):
            print(perm)
    ```

Du meilleur au pire :  
**O(1) < O(log n) < O(n) < O(n²) < O(n³) < O(n!)**

---
