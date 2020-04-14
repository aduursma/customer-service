CREATE TABLE IF NOT EXISTS customers (

    ID SERIAL PRIMARY KEY,
    VERSION INT NOT NULL DEFAULT 0,
    CREATED TIMESTAMP,
    LAST_UPDATED TIMESTAMP,
    NAME VARCHAR(20) NOT NULL

);

INSERT INTO customers (name) VALUES
    ('Robert C. Martin'),
    ('Martin Fowler'),
    ('Grady Booch'),
    ('Kent Beck'),
    ('Erich Gamma')
;
