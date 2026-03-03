-- PostgreSQL: structure de la base public

-- (optionnel) supprimer le schéma s'il existe déjà
-- DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA IF NOT EXISTS public;
SET search_path TO public;

-- -----------------------------------------------------
-- Table public.roles
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.roles CASCADE;

CREATE TABLE IF NOT EXISTS public.roles (
  id         integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title      varchar(45) NOT NULL,
  created_at timestamp    NOT NULL DEFAULT NOW(),
  updated_at timestamp    NOT NULL DEFAULT NOW(),
  deleted_at timestamp    NULL DEFAULT NULL
);

-- -----------------------------------------------------
-- Table public.users
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.users CASCADE;

CREATE TABLE IF NOT EXISTS public.users (
  id              integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username        varchar(45)  NOT NULL,
  avatar          varchar(100),
  email           varchar(45)  NOT NULL,
  email_verified_at timestamp  NULL DEFAULT NULL,
  password        varchar(255),
  remember_token  varchar(100) DEFAULT NULL,
  created_at      timestamp    NOT NULL DEFAULT NOW(),
  updated_at      timestamp    NOT NULL DEFAULT NOW(),
  deleted_at      timestamp    NULL DEFAULT NULL,
  roles_id        integer      NOT NULL DEFAULT 1,
  CONSTRAINT email_unique      UNIQUE (email),
  CONSTRAINT fk_users_roles1   FOREIGN KEY (roles_id)
    REFERENCES public.roles (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_users_roles1_idx ON public.users (roles_id);

-- -----------------------------------------------------
-- Table public.categories
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.categories CASCADE;

CREATE TABLE IF NOT EXISTS public.categories (
  id         integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title      varchar(100),
  created_at timestamp NOT NULL DEFAULT NOW(),
  updated_at timestamp NOT NULL DEFAULT NOW(),
  deleted_at timestamp NULL DEFAULT NULL
);

-- -----------------------------------------------------
-- Table public.articles
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.articles CASCADE;

CREATE TABLE IF NOT EXISTS public.articles (
  id            integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title         varchar(255) NOT NULL,
  slug          varchar(255) NOT NULL,
  content       text         NOT NULL,
  cover         varchar(100) NOT NULL,
  created_at    timestamp    NOT NULL DEFAULT NOW(),
  updated_at    timestamp    NOT NULL DEFAULT NOW(),
  deleted_at    timestamp    NULL DEFAULT NULL,
  users_id      integer      NOT NULL,
  categories_id integer      NOT NULL,
  CONSTRAINT fk_articles_users1 FOREIGN KEY (users_id)
    REFERENCES public.users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_articles_categories1 FOREIGN KEY (categories_id)
    REFERENCES public.categories (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_articles_users1_idx
  ON public.articles (users_id);

CREATE INDEX fk_articles_categories1_idx
  ON public.articles (categories_id);

-- -----------------------------------------------------
-- Table public.comments
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.comments CASCADE;

CREATE TABLE IF NOT EXISTS public.comments (
  id         integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  content    text      NOT NULL,
  created_at timestamp NOT NULL DEFAULT NOW(),
  updated_at timestamp NOT NULL DEFAULT NOW(),
  deleted_at timestamp NULL DEFAULT NULL,
  users_id   integer   NOT NULL,
  articles_id integer  NOT NULL,
  CONSTRAINT fk_comments_users1 FOREIGN KEY (users_id)
    REFERENCES public.users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_comments_articles1 FOREIGN KEY (articles_id)
    REFERENCES public.articles (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_comments_users1_idx
  ON public.comments (users_id);

CREATE INDEX fk_comments_articles1_idx
  ON public.comments (articles_id);

-- -----------------------------------------------------
-- Table public.users_has_articles
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.users_has_articles CASCADE;

CREATE TABLE IF NOT EXISTS public.users_has_articles (
  users_id    integer NOT NULL,
  articles_id integer NOT NULL,
  PRIMARY KEY (users_id, articles_id),
  CONSTRAINT fk_users_has_articles_users1 FOREIGN KEY (users_id)
    REFERENCES public.users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users_has_articles_articles1 FOREIGN KEY (articles_id)
    REFERENCES public.articles (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_users_has_articles_articles1_idx
  ON public.users_has_articles (articles_id);

CREATE INDEX fk_users_has_articles_users1_idx
  ON public.users_has_articles (users_id);

-- -----------------------------------------------------
-- Table public.tags
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.tags CASCADE;

CREATE TABLE IF NOT EXISTS public.tags (
  id         integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title      varchar(45) NOT NULL,
  created_at timestamp NOT NULL DEFAULT NOW(),
  updated_at timestamp NOT NULL DEFAULT NOW(),
  deleted_at timestamp NULL DEFAULT NULL
);

-- -----------------------------------------------------
-- Table public.articles_has_tags
-- -----------------------------------------------------
DROP TABLE IF EXISTS public.articles_has_tags CASCADE;

CREATE TABLE IF NOT EXISTS public.articles_has_tags (
  articles_id integer NOT NULL,
  tags_id     integer NOT NULL,
  PRIMARY KEY (articles_id, tags_id),
  CONSTRAINT fk_articles_has_tags_articles1 FOREIGN KEY (articles_id)
    REFERENCES public.articles (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_articles_has_tags_tags1 FOREIGN KEY (tags_id)
    REFERENCES public.tags (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX fk_articles_has_tags_tags1_idx
  ON public.articles_has_tags (tags_id);

CREATE INDEX fk_articles_has_tags_articles1_idx
  ON public.articles_has_tags (articles_id);