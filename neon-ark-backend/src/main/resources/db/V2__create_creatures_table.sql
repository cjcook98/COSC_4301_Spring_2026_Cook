CREATE TABLE creatures (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    habitat_id INTEGER NOT NULL REFERENCES habitats(id),
    UNIQUE (habitat_id, name)
);