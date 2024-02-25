--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS orders
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    product_count INT NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    status VARCHAR NOT NULL
);

--rollback DROP TABLE IF EXISTS orders;