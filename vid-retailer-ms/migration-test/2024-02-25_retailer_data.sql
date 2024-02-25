--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.retailer (id, name)
VALUES (1, 'Спортмастер')
ON CONFLICT DO NOTHING;