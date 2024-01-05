--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS payment
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    mode VARCHAR NOT NULL,
    order_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    status VARCHAR NOT NULL
);

--rollback DROP TABLE IF EXISTS payment;