CREATE TABLE IF NOT EXISTS CUSTOMERS (

    ID SERIAL PRIMARY KEY,
    VERSION INT NOT NULL DEFAULT 0,
    CREATED TIMESTAMP NOT NULL,
    LAST_UPDATED TIMESTAMP,
    NAME VARCHAR(20) NOT NULL

);

INSERT INTO CUSTOMERS (CREATED, LAST_UPDATED, NAME) VALUES
    (current_timestamp, current_timestamp, 'Robert C. Martin'),
    (current_timestamp, current_timestamp, 'Martin Fowler'),
    (current_timestamp, current_timestamp, 'Grady Booch'),
    (current_timestamp, current_timestamp, 'Kent Beck'),
    (current_timestamp, current_timestamp, 'Erich Gamma')
;
