-- psql login
-- psql -h localhost -d postgres

-- table作成
CREATE TABLE menu
(
    id    smallserial  NOT NULL,
    title varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE ingredient
(
    ingredient_id serial       NOT NULL,
    FOREIGN KEY (id) REFERENCES menu (id),
    id            smallint     NOT NULL,
    item          varchar(255) NOT NULL,
    quantity      real         NOT NULL,
    scale         varchar(255) NOT NULL,
    PRIMARY KEY (ingredient_id)
);
CREATE TABLE seasoning
(
    seasoning_id serial       NOT NULL,
    FOREIGN KEY (id) REFERENCES menu (id),
    id           smallint     NOT NULL,
    item         varchar(255) NOT NULL,
    quantity     real         NOT NULL,
    scale        varchar(255) NOT NULL,
    PRIMARY KEY (seasoning_id)
);

-- data確認
SELECT *
FROM menu;
SELECT *
FROM ingredient;
SELECT *
FROM seasoning;

-- table削除
DROP TABLE menu;
DROP TABLE ingredient;
DROP TABLE seasoning;