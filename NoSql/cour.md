# 16/12/2025 NoSql Premier cour

Le NoSQl est une représentation de petites quantités de données mais en énorme volume.  
Représentation en JSON, avec par exemple mongodb

# 03/04/2026 Création d'un base mongo
### Base mongo via docker
Avec le fichier `docker-compose.yml` et `docker-compose.mongo-test.yml`, on peut créer une base mongo via docker :  
A la racine du projet :
```bash
docker compose -f docker-compose.yml -f "NoSql/docker-compose.mongo-test.yml" up -d
```

### Connexion au container puis a MongoDB
```powershell
# 1) Ouvrir le shell Mongo depuis le container
docker exec -it nosql-mongo-test mongosh

# 2) Dans le shell, selectionner la base
use test
```

## mongo_db cheatsheet

### Acces / shell / meta
- `mongosh` : lance le shell MongoDB.
- `mongosh --version` : affiche la version du shell.
- `db.help()` : aide des commandes.
- `db.stats()` : stats sur la base courante.

### Bases et collections
- `show dbs` : liste les bases presentes sur le serveur.
- `use nomdelabase` : selectionne (ou cree logiquement) une base.
- `db` : affiche la base courante.
- `db.dropDatabase()` : supprime la base courante.
- `show collections` : liste les collections de la base courante.
- `db.createCollection("Name")` : cree une collection.
- `db.<collection>.drop()` : supprime une collection.
- `db.<source>.renameCollection("destination")` : renomme une collection.

### Insertion
- `db.<collection>.insertOne({ ... })` : insere un document.
- `db.<collection>.insertMany([ ... ])` : insere plusieurs documents.

### Lecture (find) + projection
- `db.<collection>.find()` : retourne les documents.
- `db.<collection>.find({ ... })` : filtre (JSON).
- `db.<collection>.find({ ... }, { champ1: 1, champ2: 1, _id: 0 })` : projection (champs inclus/exclus).
- `db.<collection>.findOne({ ... })` : un seul document.
- `db.<collection>.find(...).pretty()` : affichage lisible.

### Update
- `db.<collection>.updateOne({ filtre }, { $set: { ... } })` : met a jour un document.
- `db.<collection>.updateMany({ filtre }, { $set: { ... } })` : met a jour plusieurs documents.
- `db.<collection>.updateMany({ filtre }, { $unset: { champ: "" } })` : supprime un champ.
- `db.<collection>.updateMany({ filtre }, { $inc: { champNum: 1 } })` : incremente un champ numerique.

### Delete
- `db.<collection>.deleteOne({ filtre })` : supprime un document.
- `db.<collection>.deleteMany({ filtre })` : supprime plusieurs documents.

### Comptage / distinct
- `db.<collection>.count()` / `db.<collection>.find().count()` : comptage (selon version du shell).
- `db.<collection>.distinct("champ")` : valeurs distinctes.

### Pagination / tri
- `db.<collection>.find(...).limit(X)` : limite.
- `db.<collection>.find(...).skip(X)` : offset.
- `db.<collection>.find(...).sort({ champ: 1 })` : tri (1 asc, -1 desc).

### Operateurs courants (filtres)
- `$gt`, `$gte`, `$lt`, `$lte`, `$ne` : comparaison.
- `$in`, `$nin` : dans / pas dans.
- `$or` : OU logique.
- `$exists` : champ present.
- `$all` : tableau contenant tous les elements specifies.