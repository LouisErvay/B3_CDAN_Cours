-- 1. Requête qui retourne tous les utilisateurs.
-- SELECT * FROM users;

-- 2. Requête qui retourne l'email de tous les utilisateurs dans l'ordre alphabétique.
-- SELECT email FROM users ORDER BY email ASC;

-- 3. Requête qui retourne le nombre d'articles
-- SELECT count(*) FROM articles;

-- 4. Requête qui retourne les 4 premiers articles du plus récent au plus ancien.
-- SELECT * FROM articles ORDER BY created_at DESC LIMIT 4;

-- 5. Requête qui retourne les articles qui ont la catégorie "Voyage" ou "Art" avec une jointure.
-- SELECT articles.*, categories.title as category FROM articles 
-- INNER JOIN categories ON articles.categories_id = categories.id 
-- WHERE categories.title = 'Travel' 
-- OR categories.title = 'Art';

-- 6. Requête qui retourne les utilisateurs dont l'email se termine par "@yahoo.fr".
-- SELECT * FROM users WHERE email LIKE '%@yahoo.fr';

-- 7. Requête qui retourne le nombre de modérateurs.
-- SELECT count(*) FROM users
-- INNER JOIN roles ON users.roles_id = roles.id
-- WHERE roles.title = 'MODERATOR';

-- 8. Requête qui retourne le dernier utilisateur enregistré.
-- SELECT * FROM users ORDER BY created_at DESC LIMIT 1;

-- 9. Requête qui retourne le ou les tags de l'article dont le titre est "Alpine JS".
-- SELECT tags.* FROM tags
-- INNER JOIN articles_has_tags ON tags.id = articles_has_tags.tags_id
-- INNER JOIN articles ON articles_has_tags.articles_id = articles.id
-- WHERE articles.title = 'Alpine JS';

-- 10. Requête qui retoune les utilisateurs dont l'email n'est pas vérifié.
-- SELECT * FROM users WHERE email_verified_at IS NULL;

-- 11. Requête qui retourne l'utilisateur qui a aimé le plus d'article.
-- SELECT users.*, count(*) as article_liked_count FROM users
-- INNER JOIN users_has_articles ON users.id = users_has_articles.users_id
-- GROUP BY users.id
-- ORDER BY article_liked_count DESC LIMIT 1;

-- 12. Requête qui retourne la catégorie la plus utilisée par les articles.
-- SELECT categories.*, count(*) as article_count FROM categories
-- INNER JOIN articles ON categories.id = articles.categories_id
-- GROUP BY categories.id
-- ORDER BY article_count DESC LIMIT 1;

-- 13. Requête qui retourne les commentaires écrit par l'utilisateur 'marise.cpz'.
-- SELECT comments.* FROM comments
-- INNER JOIN users ON comments.users_id = users.id
-- WHERE users.username = 'marise.cpz';

-- 14. Requête qui retourne les titres et la catégorie des articles de manière concaténée.
-- SELECT articles.title, categories.title as category FROM articles
-- INNER JOIN categories ON articles.categories_id = categories.id;

-- 15. Requête qui retourne la moyenne des 'j'aime' des articles.
-- SELECT AVG(likes_count) as average_likes FROM (
--     SELECT articles.id, count(*) as likes_count FROM articles
--     INNER JOIN users_has_articles ON articles.id = users_has_articles.articles_id
--     GROUP BY articles.id
-- ) as article_likes;

-- 16. Requête qui retourne les articles du plus aimé au moins aimé.
-- SELECT articles.*, count(*) as likes_count FROM articles
-- INNER JOIN users_has_articles ON articles.id = users_has_articles.articles_id
-- GROUP BY articles.id
-- ORDER BY likes_count DESC;