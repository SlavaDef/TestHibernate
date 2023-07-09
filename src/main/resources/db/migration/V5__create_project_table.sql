CREATE TABLE project(
id IDENTITY PRIMARY KEY,
project_name VARCHAR(200)

);

CREATE TABLE person_project(
person_id BIGINT,
project_id BIGINT,
FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE,
FOREIGN KEY(project_id) REFERENCES project(id) ON DELETE CASCADE

);