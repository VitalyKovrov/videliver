--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.product (id, retailer_id, name, price, total_count)
VALUES (1, 1, 'Стул', 100.00, 10)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, retailer_id, name, price, total_count)
VALUES (2, 1, 'Стол', 200.00, 20)
ON CONFLICT DO NOTHING;

INSERT INTO public.product (id, retailer_id, name, price, total_count)
VALUES (3, 1, 'Шкаф', 300.00, 30)
ON CONFLICT DO NOTHING;