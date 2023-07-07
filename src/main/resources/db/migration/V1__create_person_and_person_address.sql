CREATE TABLE person (
    id IDENTITY PRIMARY KEY
);

CREATE TABLE person_address (
    person_id BIGINT,
    address VARCHAR(100),
    FOREIGN KEY (person_id) REFERENCES person(id)
);