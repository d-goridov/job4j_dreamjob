create table IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name varchar(255),
    email varchar(255),
    password varchar(255),
    CONSTRAINT email_unique UNIQUE(email)
);