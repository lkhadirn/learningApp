-- V2__create_employee_table.sql
CREATE TABLE employee (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          company_id INTEGER REFERENCES company(id),
                          created_at TIMESTAMP NOT NULL
);
