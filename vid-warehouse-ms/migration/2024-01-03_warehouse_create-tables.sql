--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS warehouse
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL,
    city VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL,
    count BIGINT NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id, warehouse_id),
    CONSTRAINT fk_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
);

--rollback DROP TABLE IF EXISTS product;
--rollback DROP TABLE IF EXISTS warehouse;