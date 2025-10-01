# GIT

## 2025/09/30

Bien sûr, voici le tableau Markdown correspondant à l'image que vous avez fournie :

### gitignore

- Cheat sheet

| Syntaxe           | Description                                                                                                                                                        |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `#`               | Utilisé pour commenter une ligne dans le fichier `.gitignore`. Les lignes débutant par `h` sont ignorées et servent à ajouter des commentaires pour la lisibilité. |
| `*`               | Correspond à tout caractère ou séquence de caractères. Par exemple, `*.txt` ignore tous les fichiers avec l'extension .txt.                                        |
| `/logs`           | Ignore le répertoire nommé "logs" à la racine du dépôt.                                                                                                            |
| `logs/`           | Ignore tous les fichiers ou répertoires nommées "logs" à partir du répertoire où se trouve le fichier `.gitignore`.                                                |
| `!important.txt`  | N'ignore pas le fichier `important.txt` même s'il est précédé par des règles d'ignorance générales.                                                                |
| `**/logs`         | Ignore le répertoire "logs" quel que soit l'endroit où il se trouve dans la hiérarchie des répertoires.                                                            |
| `*.log`           | Ingore tous les fichiers ayant l'extension `.log`.                                                                                                                 |
| `config*.txt`     | Ignore tous les fichiers commençant par "config" et ayant l'extension `.txt`.                                                                                      |
| `logs/*.log`      | Ignore tous les fichiers avec l'extension `.log` dans le répertoire `logs`.                                                                                        |
| `/logs/debug/`    | Ignore le répertoire "debug" dans le répertoire "logs" situé à la racine du dépot.                                                                                 |
| `/*.tmp`          | Ignore tous les fichiers avec l'extension `.tmp` à la racine du dépot.                                                                                             |
| `/vender/*/*.dll` | Ignore tous les fichiers `.dll` dans tous les sous-répertoires de `vendor`.                                                                                        |

---

- Si j'ai oublié de gitignore un fichier et que je l'ai commit :\
  Il est `tracked`, pour le un-track il faut :\
  `git rm --cached <file>`
