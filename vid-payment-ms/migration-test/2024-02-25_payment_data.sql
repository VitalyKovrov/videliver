--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.customer (id, available_amount, reserved_amount)
VALUES (1, 1000.00, 0)
ON CONFLICT DO NOTHING;

INSERT INTO public.customer (id, available_amount, reserved_amount)
VALUES (2, 1000.00, 0)
ON CONFLICT DO NOTHING;

INSERT INTO public.customer (id, available_amount, reserved_amount)
VALUES (3, 1000.00, 0)
ON CONFLICT DO NOTHING;