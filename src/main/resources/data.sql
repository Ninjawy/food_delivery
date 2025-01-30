INSERT INTO user_types (type_name)
VALUES ('customer'),
       ('admin'),
       ('restaurant owner') ON CONFLICT (type_name) DO NOTHING;

INSERT INTO roles (role_id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'CUSTOMER') ON CONFLICT (role_name) DO NOTHING;
