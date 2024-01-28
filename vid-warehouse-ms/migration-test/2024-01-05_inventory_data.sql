--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.inventory (product_id, quantity)
VALUES (1, 100)
ON CONFLICT DO NOTHING;

INSERT INTO public.inventory (product_id, quantity)
VALUES (2, 200)
ON CONFLICT DO NOTHING;

INSERT INTO public.inventory (product_id, quantity)
VALUES (3, 300)
ON CONFLICT DO NOTHING;