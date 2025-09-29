# Cour du 2025/09/24 : Architecture distribuée - Web service

### Web service :

Un web service est un programme accéssible qui permet de récupérer des données.  
Une API est un web service.  
Une API n'est pas forcément REST.

**Protocoles :**

Grâce au protocole HTTP, on à accès à plusieurs types de requettes.  
99% des requettes sont en :

- GET
  - Permet de récupérer des données.
  - Les informations sont passables seulement via les paramètres de l'URL
- POST
  - Généralement utilisé pour des échanges de données (envoie ou réception)
  - Accepte le "Content-type" dans le header, qui permet par exemple d'envoyer du JSON
- PUT
  - Généralement utilisé pour créer des données en DB
  - Accepte le "Content-type" dans le header, qui permet par exemple d'envoyer du JSON
- DELETE
  - Sert généralement à la supression d'élément dans la DB.
  - Les informations sont passables seulement via les paramètres de l'URL

**Les codes HTTP :**

- 200 .. 299
  - Codes de succès, tout va bien
- 300 .. 399
  - Codes de redirection
- 400 .. 499
  - Codes de mauvais requette (mauvais URL, erreur dans les données envoyés...)
- 500 .. 599
  - Erreur server
