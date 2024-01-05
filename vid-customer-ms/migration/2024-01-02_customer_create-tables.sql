--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS customer
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    email VARCHAR UNIQUE
);

--rollback DROP TABLE IF EXISTS customer;