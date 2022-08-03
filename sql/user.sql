-- psql login
-- psql -h localhost -d postgres

-- table作成
CREATE TABLE menus
(
    id    int          NOT NULL,
    title varchar(255) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE ingredient
(
    ingredient_id int          NOT NULL,
    FOREIGN KEY (id) REFERENCES menus (id),
    id            int          NOT NULL,
    item          varchar(255) NOT NULL,
    quantity      int          NOT NULL,
    weight        int          NOT NULL,
    PRIMARY KEY (ingredient_id)
);

-- data注入
INSERT INTO menus (id, title)
VALUES (1, '唐揚げ'),
       (2, 'ステーキ'),
       (3, 'ビフテキ'),
       (4, '肉じゃが'),
       (5, 'カレー');
INSERT INTO ingredient (ingredient_id, id, item, quantity, weight)
VALUES (1, 1, '鶏肉', 1, 200),
       (2, 2, '牛肉', 1, 500),
       (3, 2, '卵', 2, 20),
       (4, 3, '牛肉', 1, 100),
       (5, 4, '牛肉', 1, 200),
       (6, 4, 'じゃがいも', 4, 40),
       (7, 5, '牛肉', 1, 300),
       (8, 5, 'にんじん', 1, 300);

-- data確認
SELECT *
FROM menus;
SELECT *
FROM ingredient;

-- table削除
DROP TABLE menus;
DROP TABLE ingredient;