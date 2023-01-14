-- V3__populate_data.sql
INSERT INTO company (name, address, created_at) VALUES
                                                    ('Company 1', 'Address 1', NOW()),
                                                    ('Company 2', 'Address 2', NOW()),
                                                    ('Company 3', 'Address 3', NOW()),
                                                    ('Company 4', 'Address 4', NOW()),
                                                    ('Company 5', 'Address 5', NOW()),
                                                    ('Company 6', 'Address 6', NOW()),
                                                    ('Company 7', 'Address 7', NOW()),
                                                    ('Company 8', 'Address 8', NOW()),
                                                    ('Company 9', 'Address 9', NOW()),
                                                    ('Company 10', 'Address 10', NOW());

INSERT INTO employee (first_name, last_name, email, company_id)
VALUES ('John', 'Doe', 'john.doe@email.com', 1),
       ('Jane', 'Doe', 'jane.doe@email.com', 1),
       ('Bob', 'Smith', 'bob.smith@email.com', 2),
       ('Samantha', 'Johnson', 'samantha.johnson@email.com', 2),
       ('Michael', 'Williams', 'michael.williams@email.com', 3),
       ('Emily', 'Jones', 'emily.jones@email.com', 3),
       ('Matthew', 'Brown', 'matthew.brown@email.com', 4),
       ('Ashley', 'Moore', 'ashley.moore@email.com', 4),
       ('Joshua', 'Taylor', 'joshua.taylor@email.com', 5),
       ('Amanda', 'Anderson', 'amanda.anderson@email.com', 5),
       ('Daniel', 'Thomas', 'daniel.thomas@email.com', 6),
       ('Melissa', 'Hernandez', 'melissa.hernandez@email.com', 6),
       ('Nicholas', 'Moore', 'nicholas.moore@email.com', 7),
       ('Lauren', 'Martin', 'lauren.martin@email.com', 7),
       ('Christopher', 'Jackson', 'christopher.jackson@email.com', 8),
       ('Stephanie', 'Thompson', 'stephanie.thompson@email.com', 8),
       ('Joseph', 'Garcia', 'joseph.garcia@email.com', 9),
       ('Rebecca', 'Martinez', 'rebecca.martinez@email.com', 9),
       ('Jonathan', 'Robinson', 'jonathan.robinson@email.com', 10),
       ('Amber', 'Clark', 'amber.clark@email.com', 10);
