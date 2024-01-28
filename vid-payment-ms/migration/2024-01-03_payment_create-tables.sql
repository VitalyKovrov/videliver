--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS payment
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    mode VARCHAR NOT NULL,
    order_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    status VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS customer
(
    id BIGINT NOT NULL PRIMARY KEY,
    available_amount DOUBLE NOT NULL,
    reserved_amount DOUBLE NOT NULL
);

--rollback DROP TABLE IF EXISTS payment;
--rollback DROP TABLE IF EXISTS customer;