--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS orders
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item VARCHAR NOT NULL,
    quantity INT NOT NULL,
    amount BIGINT NOT NULL,
    status VARCHAR NOT NULL
);

--rollback DROP TABLE IF EXISTS orders;