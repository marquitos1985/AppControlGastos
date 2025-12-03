INSERT INTO users (first_name,last_name, email, password)
VALUES ('User', 'uno', 'user1@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa1');

INSERT INTO users (first_name,last_name, email, password)
VALUES ('User', 'dos', 'user2@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa2');

INSERT INTO users (first_name,last_name, email, password)
VALUES ('User', 'tres', 'user3@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa3');

INSERT INTO users (first_name,last_name, email, password)
VALUES ('User', 'cuatro', 'user4@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa4');

INSERT INTO users (first_name,last_name, email, password)
VALUES ('User', 'cinco', 'user5@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa5');





INSERT INTO cost_origins (name, description, user_id)
VALUES ('Casa', 'Mi casa', 1);

INSERT INTO cost_origins (name, description, user_id)
VALUES ('Casa', 'Mi oficina', 2);

INSERT INTO cost_origins (name, description, user_id)
VALUES ('Casa', 'Mi laboratorio', 3);

INSERT INTO cost_origins (name, description, user_id)
VALUES ('Casa', 'Mi quincho', 4);

INSERT INTO cost_origins (name, description, user_id)
VALUES ('Casa', 'Mi local', 5);





INSERT INTO categories (name, description, user_id)
VALUES ('Servicios', 'Servicios', 1);

INSERT INTO categories (name, description, user_id)
VALUES ('Impuestos', '', 2);

INSERT INTO categories (name, description, user_id)
VALUES ('Varios', 'Gastos generales', 1);

INSERT INTO categories (name, description, user_id)
VALUES ('Alimento', 'Comida de la casa', 2);





INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Patente auto', 'OHM750', 45000, 'ARS', 2, 1);

INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Agua', 'Osse', 30000, 'ARS', 1, 1);

INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Gas', 'Camuzzi', 100000, 'ARS', 1, 1);

INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Patente auto', 'OHM750', 45000, 'ARS', 2, 5);

INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Agua', 'Osse', 30000, 'ARS', 1, 5);

INSERT INTO costs (expiration_date, name, description, amount, currency, category_id, cost_origin_id)
VALUES ('1985-10-10', 'Gas', 'Camuzzi', 100000, 'ARS', 1, 5);







INSERT INTO incomes (name, entry_date, income_type, amount, currency, description, user_id)
VALUES ('Sueldo', '2025-10-01', 'MONTHLY', 2000000, 'ARS', 'Telecom', 1);

INSERT INTO incomes (name, entry_date, income_type, amount, currency, description,user_id)
VALUES ('Préstamo', '2025-10-01', 'ONLY', 3000000, 'ARS','BBVA', 1);




INSERT INTO income_per_cost_origin (income_id, cost_origin_id)
VALUES (1, 1);

INSERT INTO income_per_cost_origin (income_id, cost_origin_id)
VALUES (1, 2);


















