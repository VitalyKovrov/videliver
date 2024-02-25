--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.warehouse (name, city)
VALUES ('Shushary', 'Saint-Petersburg')
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (1, 1, 10)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (2, 1, 20)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (3, 1, 30)
ON CONFLICT DO NOTHING;