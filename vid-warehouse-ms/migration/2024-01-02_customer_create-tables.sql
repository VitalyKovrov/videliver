--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS customer
(
    id          VARCHAR NOT NULL
        CONSTRAINT pk_customer PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
);

--rollback DROP TABLE IF EXISTS customer;