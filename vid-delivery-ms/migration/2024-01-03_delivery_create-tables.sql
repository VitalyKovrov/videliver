--liquibase formatted sql

--changeset vkovrov@it-one.ru:create_tables
--comment: Создание таблиц

CREATE TABLE IF NOT EXISTS shipment
(
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    address VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    order_id BIGINT NOT NULL,
);

--rollback DROP TABLE IF EXISTS shipment;