-- psql login
-- psql -h localhost -d postgres

-- table作成
CREATE TABLE menus
(
    id    int          NOT NULL,
    title varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

-- data注入
INSERT INTO menus (id, title)
VALUES (1, '唐揚げ'),
       (2, 'ステーキ');

-- data確認
select *
from menus;

-- table削除
truncate menus;