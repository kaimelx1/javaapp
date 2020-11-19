DELETE FROM user_roles;
DELETE FROM auto;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, email, password) VALUES
  ('Viewer', 'viewer@yandex.ru', 'password'),
  ('Editor', 'editor@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('VIEWER', 2),
       ('EDITOR', 1);

INSERT INTO auto (user_id, brand, model, body, color, power, fuel) VALUES
  (1, 'VW', 'B5', 'SEDAN', 'BLACK', 140, 'DIESEL');
