CREATE TABLE IF NOT EXISTS customers (

    id SERIAL PRIMARY KEY,
    version INT NOT NULL DEFAULT 0,
    created TIMESTAMP,
    last_updated TIMESTAMP,
    name VARCHAR(20)

);

INSERT INTO customers (name) VALUES
    ('Andre Duursma'),
    ('Jeroen Burggraaf'),
    ('Cees Sinke'),
    ('Ryan Fester'),
    ('David Pardo')
;
