--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS product
(
    id BIGINT NOT NULL PRIMARY KEY,
    retailer_id BIGINT NOT NULL,
    name  VARCHAR NOT NULL,
    description TEXT,
    total_count BIGINT NOT NULL,
    price DOUBLE
);

--rollback DROP TABLE IF EXISTS product;