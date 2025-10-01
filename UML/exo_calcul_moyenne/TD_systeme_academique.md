1. Campus
2. Departement
3. Enseignant
4. Eleve
5. Cour ?
6. Note
7. Matiere
8. Salle (useless ?)

Un campus à plusieurs département  
Un Département à plusieurs enseignants  
Un département à un enseignant responsable  
Un enseignant enseigne 1 manière  
Un eleve à plusieurs matières  
un élève a 1 note par matière  
Une matière a une salle de cour

Calculer la moyenne des notes :

- Par eleve
- Par matiere
- Par département

### Définition des acteurs et du système:

**Système :** SAGM Système Académique de Gestion de Moyenne

**Acteurs :**

1. Enseignant
2. Eleve
3. Responsable

### Les cas d'utilisation :

0. **Calculer une moyenne**

   - **Objectif**  
      Calculer une moyenne
   - **Acteur concernés**  
      Enseignant, Eleve, Responsable
   - **Pré conditions**  
      Au moins une note dans le système.
   - **Post conditions**  
      ...
   - **Sénario nominal**  
      l'acteur demande le calcul d'un moyenne avec son id  
      le système calcul
   - **Sénario alternatif**  
      ...

1. **Un élève calcul sa moyenne**

   - **Objectif**  
      Un élève consulte la moyenne de toutes ses notes
   - **Acteur concernés**  
      Un élève
   - **Pré conditions**  
      Au moins une note doit avoir été enregistré pour l'élève demandeur
   - **Post conditions**  
      ...
   - **Sénario nominal**  
      L'élève demande au système de connaitre sa moyenne
     Le système calcul la moyenne avec toutes les notes de l'élève
     Le système rend à l'élève sa moyenne
   - **Sénario alternatif**  
      Le système n'a pas pu calculer la moyenne
     Une réponse "erreur" est rendue à l'élève par le système

2. **Le responsable de département calcul la moyenne de son département**

   - **Objectif**  
      Le responsable de département consulte la moyenne des notes d'un département
   - **Acteur concernés**  
      Responsable
   - **Pré conditions**  
      Au moins une note doit avoir été enregistré dans le département
   - **Post conditions**  
      ...
   - **Sénario nominal**  
      Le responsable de département demande au système la moyenne des notes d'un département

   - **Sénario alternatif**  
      Le système n'a pas pu calculer la moyenne
     Une réponse "erreur" est rendue à l'élève par le système

3. **Un enseignant responsable calcul la moyenne d'une matière**

   - **Objectif**  
      Un enseignant calcul la moyenne de sa maière
   - **Acteur concernés**  
      Enseignant
   - **Pré conditions**  
      Au moins une note dans la matière demandée.
   - **Post conditions**  
      ...
   - **Sénario nominal**  
      L'enseignant
   - **Sénario alternatif**  
      ...
