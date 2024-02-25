--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS retailer
(
    id          VARCHAR NOT NULL
        CONSTRAINT pk_retailer PRIMARY KEY,
    name  VARCHAR NOT NULL
);

--rollback DROP TABLE IF EXISTS retailer;