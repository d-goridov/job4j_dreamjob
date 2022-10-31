create table IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    name varchar,
    email varchar,
    password varchar,
    CONSTRAINT email_unique UNIQUE(email)
);