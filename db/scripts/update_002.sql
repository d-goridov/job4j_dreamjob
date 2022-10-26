create table candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created date,
    city_id integer
);