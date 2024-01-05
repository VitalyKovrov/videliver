--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.inventory (item, quantity)
VALUES ('Стол', 100)
ON CONFLICT DO NOTHING;

INSERT INTO public.inventory (item, quantity)
VALUES ('Диван', 200)
ON CONFLICT DO NOTHING;

INSERT INTO public.inventory (item, quantity)
VALUES ('Шкаф', 300)
ON CONFLICT DO NOTHING;