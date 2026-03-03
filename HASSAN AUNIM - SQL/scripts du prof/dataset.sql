-- ROLES: COUNT 3
INSERT INTO roles (title) VALUES ('ADMINISTRATOR');
INSERT INTO roles (title) VALUES ('MODERATOR');
INSERT INTO roles (title) VALUES ('MEMBER');

-- USERS: COUNT 7
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('denise.rstl', 'avatars/001.png', 'denise.rosental@yahoo.fr', '2020-01-03 00:00:01', '$2y$10$6SrBohOErV/9XkpPLJ6LSOyLIYSNqzBgpilKbXaj7Zk49NECqGE8W', 1);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('charles.lry', 'avatars/002.png', 'charles.leroy@gmail.com', '2017-01-01 00:00:01', '$4y$10$6SrBohOErV/9XmLpoJ6LSOyLIYSMi8qgpilKbmep7Zk49NECq96lk', 2);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('john.doe', 'avatars/003.png', 'john.doe@wanadoo.fr', NULL, '$63$10$6hOErV/9XkpPLJ6LSOyLIYSNqzBaece32eda53e5b61aa02e2298bf43', 2);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('maxime.tdu', 'avatars/004.png', 'maxime.tardieu@gmail.com', NULL, '$1z$10$6SrBohOErV/9XkpPfruieHUbr8BgpilKbXaj7Zk49NECqpoLm1', 3);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('jeanne.dpx', 'avatars/005.png', 'jeanne.despiaux@gmail.com', '2016-06-01 00:00:01', '$6h$p5$6SrBohOErV/9XkpPfruieHUbr8B2eda53e5b61aa02qpoMks', 3);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('marise.cpz', 'avatars/006.png', 'marise.coupez@outlook.fr', NULL, '$5a$10$6SrBohOOErV/9XkpPfruieHUbryLIYSNqzBgpillaj7Zk49NECqGE3W', 3);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('nicole.mnl', 'avatars/007.png', 'nicole.menel@yahoo.fr', '2016-04-02 00:00:01', '$8d$84$6SrBohOErV/9XkpPLJ6LSOyLIYSNqzBgpilKbXaj7Zk49NECqGE1W', 3);
INSERT INTO users (username, avatar, email, email_verified_at, password, roles_id) VALUES ('merlin.jss', 'avatars/008.png', 'merlin.jauss@gmail.com', NULL, '$88$1f$6S6LSOyplrV/9XkpPLJ6LSOyLIYSNqzBgpilKbXaj7Zk49NECqGE1Z', 3);

-- CATEGORIES: COUNT 6
INSERT INTO categories (title) VALUES ('Cryptocurrency');
INSERT INTO categories (title) VALUES ('Web development');
INSERT INTO categories (title) VALUES ('Travel');
INSERT INTO categories (title) VALUES ('Chess');
INSERT INTO categories (title) VALUES ('Art');
INSERT INTO categories (title) VALUES ('Music');

-- ARTICLES: COUNT 8
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('London system', 'london-system', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/001.png', 4, 1);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('Alpine JS', 'alpine-js', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/002.png', 2, 1);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('Tailwind CSS', 'tailwind-css', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/003.png', 2, 1);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('Wine of Argentina', 'wine-of-argentina', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/004.png', 3, 1);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('Bitcoin', 'bitcoin', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/005.png', 1, 1);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('Digital painting', 'digital-painting', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/006.png', 5, 2);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('MAO Ableton live', 'mao-ableton-live', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/007.png', 6, 2);
INSERT INTO articles (title, slug, content, cover, categories_id, users_id) VALUES ('TypeScript', 'typescript', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam commodo egestas dolor, sit amet convallis massa vulputate id. Donec luctus sem ut leo tempus dignissim.', 'covers/008.png', 2, 1);

-- COMMENTS: COUNT 1
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 45', 2, 1);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 81', 1, 2);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 76', 3, 2);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 21', 4, 2);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 65', 7, 8);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 15', 6, 6);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 27', 5, 2);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 33', 4, 1);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 98', 3, 3);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 94', 6, 7);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 56', 1, 7);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 23', 2, 8);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 54', 2, 2);
INSERT INTO comments (content, users_id, articles_id) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. 11', 5, 2);

-- TAGS: COUNT 10
INSERT INTO tags (title) VALUES ('painting');
INSERT INTO tags (title) VALUES ('web');
INSERT INTO tags (title) VALUES ('code');
INSERT INTO tags (title) VALUES ('data');
INSERT INTO tags (title) VALUES ('chess');
INSERT INTO tags (title) VALUES ('tablet');
INSERT INTO tags (title) VALUES ('draw');
INSERT INTO tags (title) VALUES ('mysql');
INSERT INTO tags (title) VALUES ('bigdata');
INSERT INTO tags (title) VALUES ('travel');

-- ARTICLES HAS TAGS (PIVOT TABLE)
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (1, 5);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (2, 2);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (2, 3);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (3, 2);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (3, 3);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (4, 10);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (6, 1);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (6, 6);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (8, 2);
INSERT INTO articles_has_tags (articles_id, tags_id) VALUES (8, 3);

-- USERS HAS ARTICLES (PIVOT TABLE), LIKES COUNT
INSERT INTO users_has_articles (users_id, articles_id) VALUES (1, 1);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (1, 2);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (1, 3);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (1, 4);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (2, 5);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (2, 6);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (3, 7);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (3, 8);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (4, 7);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (7, 7);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (2, 4);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (6, 1);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (6, 2);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (4, 8);
INSERT INTO users_has_articles (users_id, articles_id) VALUES (5, 1);