create table employment
(
    id serial primary key,
    employee_id integer references employee,
    start_date date not null,
    wage decimal(10,2) not null,
    title varchar(255) not null,
    created_at timestamp default now() not null,
    modified_at timestamp default now() not null
);
