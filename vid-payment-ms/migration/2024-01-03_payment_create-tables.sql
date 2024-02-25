--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS customer
(
    id BIGINT NOT NULL PRIMARY KEY,
    available_amount DOUBLE PRECISION NOT NULL,
    reserved_amount DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS payment
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    mode VARCHAR NOT NULL,
    order_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    status VARCHAR NOT NULL,
    CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

--rollback DROP TABLE IF EXISTS customer;
--rollback DROP TABLE IF EXISTS payment;