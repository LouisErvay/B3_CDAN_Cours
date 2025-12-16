# Exercice de calcul de complexité selon les types de données JAVA

Pour chaque cas :  
Calculer les complexités temporelles (pire cas).  
Calculer les complexités temporelles (cas moyen).  
Calculer la complexité spatiale.

## Type Array :

-   ### **Lecture / Ecriture** :

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

-   ### **Parcourir le tableau** :

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

-   ### **Rechercher un élément (non trié)** :

    | Cas          | Complexité                 |
    | ------------ | -------------------------- |
    | Meilleur cas | **O(1)** (premier élément) |
    | Cas moyen    | **O(n)**                   |
    | Pire cas     | **O(n)**                   |

---

Complexité temporelle (pire cas) : **O(n)**  
Quand on parcourt le tableau.
Compléxité spatiale : **O(n)**  
L'espace mémoire utilisé est proportionnel au nombre d'éléments stockés.

---

## Type Pile (Stack) :

-   ### **Push / Pop** :

    On ajoute ou on retire toujours au sommet

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Pire cas     | **O(1)**   |

---

-   ### **Rechercher un élément (.contains())** :

    contains() nécessite de parcourir la pile

    | Cas          | Complexité                 |
    | ------------ | -------------------------- |
    | Meilleur cas | **O(1)** (premier élément) |
    | Cas moyen    | **O(n)**                   |
    | Pire cas     | **O(n)**                   |

---

Complexité temporelle (pire cas) : **O(n)**  
Quand on parcourt la pile.
Compléxité spatiale : **O(n)**  
L'espace mémoire utilisé est proportionnel au nombre d'éléments stockés.

---

## Type File (Queue) :

-   ### **Enqueue / Dequeue)** :

    On ajoute toujours en **fin de file** et on retire toujours en **début de file** (FIFO)

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(1)**   |

---

-   ### **Consulter le premier élément (peek)** :

    Accès direct à la tête de la file, sans suppression

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(1)**   |
    | Cas moyen | **O(1)**   |

---

-   ### **Rechercher un élément (.contains())** :

    contains() nécessite de parcourir toute la file

    | Cas          | Complexité                 |
    | ------------ | -------------------------- |
    | Meilleur cas | **O(1)** (premier élément) |
    | Cas moyen    | **O(n)**                   |
    | Pire cas     | **O(n)**                   |

---

-   ### **Parcourir la file** :

    Chaque élément est visité une fois

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

Complexité temporelle (pire cas) : **O(n)**
Lors d’une recherche ou d’un parcours complet de la file.
Compléxité spatiale : **O(n)**
L’espace mémoire utilisé est proportionnel au nombre d’éléments stockés.

---

## Type Liste chaînée (LinkedList) :

-   ### **Ajouter / Supprimer en tête ou fin (addFirst / removeFirst / addLast / removeLast)** :

    Les opérations se font directement sur le premier nœud de la liste

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(1)**   |

---

-   ### **Insertion / Suppression à une position donnée (add(i, x) / remove(i))** :

    Nécessite de parcourir la liste jusqu’à la position i

    | Cas       | Complexité |
    | --------- | ---------- |
    | Cas moyen | **O(n)**   |
    | Pire cas  | **O(n)**   |

---

-   ### **Accès par indice (get(i))** :

    La liste doit être parcourue depuis la tête ou la fin jusqu’à l’indice demandé

    | Cas       | Complexité |
    | --------- | ---------- |
    | Cas moyen | **O(n)**   |
    | Pire cas  | **O(n)**   |

---

-   ### **Parcours de la liste** :

    Parcours naturel de la structure chaînée, élément par élément

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

Complexité temporelle (pire cas) : **O(n)**
Lors d’un accès par indice, d’une insertion ou d’une suppression en position arbitraire.
Compléxité spatiale : **O(n)**
Chaque élément stocke la valeur ainsi que des références vers le nœud précédent et suivant.

---

## Type Liste doublement chaînée (DoublyLinkedList) :

-   ### **Ajouter / Supprimer en tête ou fin (addFirst / removeFirst / addLast / removeLast)** :

    La tête possède une référence directe vers le premier nœud

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(1)**   |

---

-   ### **Accès par indice (get(i))** :

    La liste est parcourue depuis la tête ou la queue selon la position de l’indice

    | Cas       | Complexité |
    | --------- | ---------- |
    | Cas moyen | **O(n)**   |
    | Pire cas  | **O(n)**   |

---

-   ### **Parcours (bidirectionnel, iterator / descendingIterator)** :

    Parcours possible dans les deux sens grâce aux références `prev` et `next`

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

Complexité temporelle (pire cas) : **O(n)**
Lors d’un accès par indice ou d’un parcours complet de la liste.
Compléxité spatiale : **O(n)**
Chaque nœud stocke la donnée ainsi que deux références (`prev` et `next`), ce qui augmente l’overhead mémoire par rapport à une liste simplement chaînée.

---

## Type Table de hachage (Hash Table) :

-   ### **Insertion (put / add)** :

    L’élément est placé directement dans le **bucket** correspondant à sa clé via la fonction de hachage.  
     Le pire cas se produit si tous les éléments se retrouvent dans le même bucket (collision extrême).

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Accès / Recherche par clé (get / containsKey)** :

    Accès direct via le hachage de la clé

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Suppression par clé (remove)** :

    On localise l’élément via la clé, puis on le supprime du bucket

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Parcours des éléments (iterator / for-each)** :

    Chaque élément du tableau de buckets et des chaînes est visité

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

Complexité temporelle (pire cas) : **O(n)**
Lorsqu’il y a de nombreuses collisions ou que tous les éléments sont dans un même bucket.
Compléxité spatiale : **O(n)**
L’espace mémoire utilisé est proportionnel au nombre d’éléments stockés, plus un overhead pour les buckets et pointeurs internes.

---

## Type Table de hachage (Hash Table) :

-   ### **Insertion (put / add)** :

    L’élément est placé directement dans le **bucket** correspondant à sa clé via la fonction de hachage.  
     Le pire cas se produit si tous les éléments se retrouvent dans le même bucket (collision extrême).

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Accès / Recherche par clé (get / containsKey)** :

    Accès direct via le hachage de la clé

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Suppression par clé (remove)** :

    On localise l’élément via la clé, puis on le supprime du bucket

    | Cas          | Complexité |
    | ------------ | ---------- |
    | Meilleur cas | **O(1)**   |
    | Cas moyen    | **O(1)**   |
    | Pire cas     | **O(n)**   |

---

-   ### **Parcours des éléments (iterator / for-each)** :

    Chaque élément du tableau de buckets et des chaînes est visité

    | Cas       | Complexité |
    | --------- | ---------- |
    | Pire cas  | **O(n)**   |
    | Cas moyen | **O(n)**   |

---

Complexité temporelle (pire cas) : **O(n)**
Lorsqu’il y a de nombreuses collisions ou que tous les éléments sont dans un même bucket.
Compléxité spatiale : **O(n)**
L’espace mémoire utilisé est proportionnel au nombre d’éléments stockés, plus un overhead pour les buckets et pointeurs internes.

---
