CREATE TABLE person_info (
person_id BIGINT PRIMARY KEY,
person_name VARCHAR(200),
FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE

)

