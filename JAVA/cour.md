# 2025/10/02 - Premier cour

### Typage et variables

- Tous les types commencent par une majuscule (Int, String, Long...)

- Deux mots clé pour déclarer une variable :

  - `var` : une variable, peut évoluer au cours du temps
  - `val` : une constante, ne peux pas évoluer au cours du temps
  - `const` : Une constante est couplée à un var ou val. Elle permet, à la compillation, de remplacer l'appel de la variable directement par la valeur de la constante.

```java
const val PRIX: Double = 1.5        // Avec const, a la compillation, PRIX sera remplacée par 1.5
var prix_total = 5 * PRIX           // Donc 5*PRIX devient 5*1.5


```

- Si on veut déclarer une variable nullable, il faut utiliser un ? :

```java
var v1:String = "lol"
v1 = null                           // Ne compile pas

var v2:String? = "lol"
v2 = null                           // ok car déclarée avec un ?, donc variable nullable
```

- Principe de **safe check** :
  - C'est le principe de checker si une variable n'est pas null avec un ? après l'appel d'une variable.
  - `v2?` vérifira que la variable n'est pas null avant de l'utiliser.

```java
var v2:String? = "Du texte"
var v3 : String = v2.uppercase()    // Ne compile pas car v2 potentiellement null

var v3 : String? = v2?.uppercase()  // Compile, car v2? vérifie que v2 != null

// le 'v2?' revient à écrire ceci pour vérifier qu'il n'est pas null :
if (v2 != null)
    v3 = v2.upperCase()
else
    v3 = null
```

- **Elvis** operator
  - Permet de rendre un résultat par défaut si une variable nullable est null lors d'un traitement

```java
var v3:String = v2 ?: "" // si v2 est null alors v3 = ""
```

```java
var resident : ResidentBean? = null
var dogName:String = resident?.leftNeighbor?.dog?.name ?: "Pas de chien"
// Ce code évite d'imbriquer des if pour vérifier que chaque variable n'est pas null alors qu'elles sont nullables.
```

---

### Fonctions

- Une fonction se déclare avec le mot clé `fun` :

```java
fun max(a: Int, b:Int) : Int {
    if(a>= b)  {
        return a
    } else {
        return b
    }
}
```

- **Type de retour** :

  - Le type de retour est déclaré après les parenthèses avec un :
  - `fun x(): Int{}` retourne un int
  - Une fonction qui ne retourne rien sera typé `Unit`, c'est l'équivalent du `void` JAVA.
  - `Any` est l'équivalent de `Object` en JAVA, et représente n'importe quel type de retour.

---

- **Fonction expression** :
  - Si une fonction ne contient qu'un return, alors on peut la compacter en une seule ligne en remplacant les `{}` par un `=` :

```java
fun max(a: Int, b:Int) : Int {
    return if(a>=b) a else b
}
```

Cette fonction peut s'écrire avec une fonction expression :

```java
fun max3(a: Int, b:Int) : Int = if(a>=b) a else b
```

- **Paramètres** dans les fonctions :

  - Il n'y a pas d'ordre obligatoire dans l'appel des paramètres quand on appelle une fonction :

```java
fun iam(surname:String, name: String)
    = println("Mon nom est $name et mon prénom est $surname")

iam("Anthony","Monteiro")
iam(surname="Anthony", name="Monteiro")
iam(name="Monteiro", surname="Anthony")
```

- Les **paramètres par défaut** :

```java
fun iam(surname: String="-", name:String? = null)
    = println("Mon nom est $name et mon prénom est $surname")

iam(surname="Anthony", name="Monteiro")
iam("Anthony")
iam(name="Monteiro")
iam()
//Plus besoin de remplir tous les paramètres, contrairement à JAVA
```

---

### Classes

- La déclaration d'un classe crée en même temps les attributs de ma class, et les différents constructeurs :

```java
data class UserBean(var email: String = "", var password: String? = null, var age: Int =0)
```

Les **attributs sont déclarés dans les parenthèses** avec es `var`.  
On leur donne aussi leur **valeur par défaut** après les `=`.  
De ce fait, **les constructeurs** avec chaque combinaisons d'attributs sont créer.  
Les **getter et setter** son également générés.

---

- On peut également déclarer des attributs dans la class (comme JAVA).  
  Ces attributs ne peuvent pas être passés à la création d'uns instance. il faut faire par la suite `obj.attribut = "value"`

- **! Important !**  
  Les getter et setter sont automatiquement crées pour chaque attributs, qu'ils aient été déclarée dans les parenthèses ou dans la class.  
  Cependant, pour les utiliser, on fait de simples appels aux attributs de l'objet que l'on manipule.  
  D'où le `user.email = ".."` qui fait bien un appel au setter de l'attribut email, mais sans que l'on le voit.

```java
class UserBean(var name: String, var age: Int) {
    var email : String? = null

    //Méthode d'instance
    fun print() = println("$name son email : $email")
}

fun main() {
    val user = UserBean("Toto", 18)
    user.email = "Toto@toto.fr"
    user.age++
    println(user.email?.uppercase())
    //appel de la méthode d'instance
    user.print()
}
```

---

- Pour faire un traitement spécifique à l'instantitaion de mon objet, je peux utiliser `init{}` qui sera le bloc de code exécuté lors de la construction de mon objet.

```java
class UserBean(var name: String, domaine: String) {
    //email peut également être initialiser dans l’init
    val email = "$name@$domaine"

    //Action à la création du constructeur primaire
    //On peut y utiliser les paramètre du constructeur
    init {
        println("Création de l’email : $email à partir du domaine $domaine")
    }
}
```

---

### Data Class

- Elles ont par défaut les méthodes :
  - toString() (Affichage)
  - Equals() (Comparaison)
  - copy() (Cloner une instance)  
    Cela **ne concerne que les attributs constructeur** (dans les parenthèses)

```java
class TownBean(val city: String, val cp: String) {
    var country: String? = null
}
data class DataTownBean(val city: String, val cp: String) {
    var country: String? = null
}

fun main(){
    val city = TownBean("Toulouse", "31000")
    city.country = "toto"
    val city2 = TownBean("Toulouse", "31000")
    city2.country = "toto2"

    val dataCity = DataTownBean("Toulouse", "31000")
    dataCity.country = "toto"
    val dataCity2 = DataTownBean("Toulouse", "31000")
    dataCity2.country = "toto2"

    println("city=$city") //city=TownBean@123245
    println("dataCity=$dataCity")//dataCity=DataTownBean(city=Toulouse, cp=31000)

    println("== : ${city == city2}") //== : false
    println("equals : ${city.equals(city2)}")// equals : false
    println("=== : ${city === city2}") //=== : false

    println("data == : ${dataCity == dataCity2}") // data == : true
    println("data equals : ${dataCity.equals(dataCity2)}")//data equals : true
    println("data === : ${dataCity === dataCity2}") //data === : false
}
```

### Singleton class

En java, une class singleton est comme ceci :

- **En java :**

```java
public class User {

    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    //Singleton
    private User() {}
    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new User();
        }
    }
}
```

- **En kotlin :**

```java
object User {
    var name: String = ""
}
```

### Boucles et conditions :

- **Mot clé `Step`** :

```java
val max = 10
for(i in 1..max step 3) {           // On itère par pas de 3
    print("$i ") //1 4 7 10
}
```

- **Mot clé `DownTo`** :

```java
val max = 10
for(i in max downTo 0 step 3) {     // On itère de max jusqu'à 0, par pas de 3
    print("$i ") //10 7 4 1
}
```

- **Mot clé `Repeat`** :

```java

repeat(5) {                         // Avec it qui représente le numéro de l'itération
    print("toto$it ")               // toto0 toto1 toto2 toto3 toto4
}

repeat(maCollection.size) {         // Parcourir une collection
    print(maCollection[it])
}
```

- **Mot clé `in`** :

```java
for(i in 3..18) {...}

if(18 !in maCollection) {...}
```

- **Itérer une String** :

```java
for (i in monString.indices ) {}            // Sans les indexs

for ((i, c) in monString.withIndex()) {     // Avec les indexs
    result += "$i : $c\n"                   // i est l'index, c le char
}
```

- **Opérateur `?:`** :

```java
val l = if (b != null) b.length else -1     // Code en JAVA Pur
val l = b?.length ?: -1                     // Version Kotlin avec Elvis
```

- **Opérateur `!!`** :
  - Génère une `NullPointerException` si la variable n'est pas null

```java
print("${toto.nom!!.toUpperCase()}")        // NullPointerException si nom est null
```

### Tableaux et ArrayList

- **Tableaux** :

```java
val tab = Array(12){ 0 }            // Déclaration | Typage

val tab2 = arrayOf("Toto", "Bob")   // Création
val tab3 = arrayOfNulls<Int>(5)     // Création d'un tableau vide (null) de taille 5

tab[0] = tab[1]                     // Lecture | Ecriture via index

tab.sum()
tab.sort()                          // IDEM que sort() JAVA, mais retourne nouvelle instance de Array

tab.reverse() / tab.reversed()
tab.min() / tab.max()
tab.first() / tab.last()

tab.take(3)                         // les 3 premiers éléments du tableau, sans le modifier
tab.drop(3)                         // le tableau sans ses 3 premiers éléments, sans le modifier
```

- **ArrayList** :

```java

val myList = ArrayList<Int>()               //Création
val myList = arrayListOf("Bob", "Bobby")    //Création + remplissage

myList.add(student)                         // Ajoute un élément, dernier index par défaut
myList.add(3, student)                      // Ajoute un élément à l'index 3
myList[3] = student                         // Ré-écrit l'indice 3
myList.addAll(otherList)                    //Ajouter une liste entière

val student = myList[3]                                 // Get valeur à l'index 3
val student : StudentBean = myList.get(3)               // Avec typage
val student : StudentBean? = myList?.getOrNull(3)       // Avec typage ou nullable

myList.remove(student)                      // Supprimer un élément
myList.remove(3)                            // Supprimer un élément via l'index
myList.clear()                              // Vider la liste

myList.foreach{ it.name += "_student" }     // Parcours de liste
```

### API REST

- **Librairie KTOR**
