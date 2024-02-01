--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.warehouse (name, city)
VALUES ('Shushary', 'Saint-Petersburg')
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (1, 1, 100)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (2, 1, 200)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, warehouse_id, count)
VALUES (3, 1, 300)
ON CONFLICT DO NOTHING;