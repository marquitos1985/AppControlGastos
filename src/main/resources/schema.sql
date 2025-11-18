CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)

);

CREATE TABLE cost_origins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)

);

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE costs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    expirationDate DATE,
    name VARCHAR(255),
    description VARCHAR(255),
    amount DOUBLE,
    currency VARCHAR(255),
    category_id INT,
    cost_origin_id INT,
    FOREIGN KEY (cost_origin_id) REFERENCES cost_origins(id)


);


CREATE TABLE cost_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    amount DOUBLE,
    currency VARCHAR(255),
    cost_id INT,
    FOREIGN KEY (cost_id) REFERENCES costs(id)

);




CREATE TABLE incomes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    entryDate DATE,
    incomeType VARCHAR(255),
    amount DOUBLE,
    currency VARCHAR(255),
    description VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)

);

CREATE TABLE income_per_cost_origin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    income_id INT,
    cost_origin_id INT,
    FOREIGN KEY (income_id) REFERENCES incomes(id),
    FOREIGN KEY (cost_origin_id) REFERENCES cost_origins(id)
);


