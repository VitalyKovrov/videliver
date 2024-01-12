--liquibase formatted sql

--changeset vkovrov@it-one.ru:test_data
--comment: Тестовые данные

INSERT INTO public.customer (first_name, last_name, email)
VALUES ('Николай', 'Николаев', 'Николаевич')
ON CONFLICT DO NOTHING;

INSERT INTO public.customer (first_name, last_name, email)
VALUES ('Иван', 'Иванов', 'Иванович')
ON CONFLICT DO NOTHING;

INSERT INTO public.customer (first_name, last_name, email)
VALUES ('Петр', 'Петров', 'Петрович')
ON CONFLICT DO NOTHING;