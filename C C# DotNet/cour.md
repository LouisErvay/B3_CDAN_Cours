# Premier cours : C, C#, .NET Intermédiaire

## Typage

- On peut utiliser le mot clé auto pour C++

```c
auto x = 10

std::vector<std::pair<std::string, int>>::const_iterator it = maCarte.begin();
auto it = maCarte.begin(); // La même chose que la ligne au dessus
```

Ici, `std::vector<std::pair<std::string, int>>::const_iterator` est la déclaration de type, retournée par la fonction maCarte.bein().  
Le mot clé `auto` permet de récupérer ce type automatiquement.

## Pointeurs

- Un pointeur est une variable qui contient **l’adresse** d’une autre variable.
- `*` sert à déclarer un pointeur ou accéder à la valeur pointée.
- `&` sert à obtenir l’adresse d’une variable.

Exemple :

```c
#include <stdio.h>

int main() {
    int x = 42;
    int *p = &x;           // p pointe vers x
    printf("%d\n", *p);    // *p = valeur pointée = 42
    return 0;
}
```

## new / delete

⚠️ En **C**, on utilise `malloc` et `free`.  
En **C++**, on a `new` et `delete` pour gérer la mémoire dynamique.

Exemple en C++ :

```c
#include <iostream>
using namespace std;

int main() {
    int *p = new int;   // allocation sur le tas
    *p = 99;
    cout << *p << endl; // affiche 99
    delete p;           // libération mémoire
    return 0;
}
```

## Pile et Tas

- **Pile (stack)** :
  - Mémoire utilisée pour les variables locales et les appels de fonction.
  - Rapide, gérée automatiquement (pas besoin de free/delete).
  - Exemple :

```c
    void f() {
        int a = 5; // a est stocké sur la pile
    }
```

- **Tas (heap)** :
  - Mémoire utilisée pour les allocations dynamiques (malloc / new).
  - Plus flexible mais il faut libérer la mémoire à la main.
  - Exemple :

```c
    #include <stdlib.h>
    #include <stdio.h>

    int main() {
        int *p = malloc(sizeof(int)); // allocation
        *p = 10;
        printf("%d\n", *p);
        free(p); // libération
        return 0;
    }
```

## Pointeurs intelligents (C++)

- En C++, pour éviter les erreurs (fuites mémoire, double delete), on utilise des **pointeurs intelligents**.
- Ils libèrent automatiquement la mémoire quand ils ne sont plus utilisés.

Exemple avec `unique_ptr` :

```c
#include <iostream>
#include <memory>
using namespace std;

class MyClass {
    public:
    MyClass() {
        cout << "Constructeur" << endl;
    }
    ~MyClass() {
        cout << "Destructeur" << endl;
    }

    void doSomething() {
        cout << "Doing something..." << endl;
    }
};

int main() {
    // Création d'un smart pointer avec auto (std::make_unique est une fonction qui retourne un unique_ptr de MyClass)
    auto myObject = make_unique<MyClass>();

    // Appelle d'une méthode de MyClass via un pointeur d'une instance
    myObject->doSomething();

    // Pas besoin de delete myObject car il est sur un smart pointer
    // La supression se fait lors de la sortie du bloc
}
```

## Variables de class

- Comme en java, les variables peuvent êtres publiques ou private.

- Pour une variable de class private, on définit également un variable du même nom publique, qui sert à accéder aux getter et setter de la variable private :

- La variable privée commence par un underscore (\_)

```c
private int _age;

public int Age {
    get { // Déclaration du getter de _age sur Age
        return _age;
    }
    set{ // Déclaration du setter de _age sur Age
        if ('valeur correspond aux attentes de la variable OK'){
            _age = value; // le mot clé 'value' est la valeur passée au setter quand la méthode est appelée
        } else {
            throw new ArgumentException("La valeur n'est pas correcte !");
        }
    }

}
```

## Héritage et polymorphisme :

- L'héritage permet à une classe d'être une classe parent d'une autre class enfant :

```c
public class Animal{
    /* ... */
}
public class Chien : Animal {
    /* ... */
}
```

Ici, la class Chien étend de la class Animal, et héritera de ses propriétés.\

- Le polymorphisme permet à une class enfant de redéfinir le comportement d'une méthode de la classe parent.

- Pour se faire, on utilise le mot clé `virtual`

```c
public class Véhicule {
    public virtual void Demarrer() {
        // Méthode pour démarrer un véhicule
    }
}

public class Moto : Vehicule {
    public override void Demarrer() {
        // Méthode pour démarrer la moto
    }
}
```

- Le mot clé `virtual` est utilisé sur les méthode de la classe parent et dit que la méthode peut être redéfinie dans la class enfant.
- Le mot clé `override` est utiilisé dans les classes enfants pour signaler que l'on redéfinit une méthode ce la classe parent.

## Abstraction & interface

- Une classe abstraite se définit avec le mot clé `abstract` dès sa définition.
