CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    full_name    VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
);
