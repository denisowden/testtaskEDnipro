CREATE TABLE students
(
    student_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    name_student VARCHAR(255) NOT NULL
);

CREATE TABLE subjects
(
    subject_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    name_subject VARCHAR(255) NOT NULL
);

CREATE TABLE students_subjects
(
    student_subject_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    student_id INT NOT NULL REFERENCES students(student_id),
    subject_id INT NOT NULL REFERENCES subjects(subject_id),
    mark INT
);

CREATE TABLE users
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE tokens
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    expiration TIMESTAMP NOT NULL,
    token VARCHAR(255) NOT NULL,
    user_id INT NOT NULL REFERENCES users(id)
);

INSERT INTO users VALUES
(1, 'admin@gmail.com', '$2a$10$SSMbcMe6kAcVJX8jW/FKR.h94BOiScTnzLgK0csx1msKybHwTDuUe', 'admin', 'admin', 'ADMIN'),
(2, 'teacher@gmail.com', '$2a$10$SSMbcMe6kAcVJX8jW/FKR.h94BOiScTnzLgK0csx1msKybHwTDuUe', 'teacher', 'teacher', 'TEACHER');

insert into students (name_student)
values
    ('Koltunov Nikita'),
    ('Guzva Yurii'),
    ('Fedash Vladislav'),
    ('Menzenko Viacheslav'),
    ('Moroz Alla'),
    ('Pokus Alina'),
    ('Kaminina Maria');

insert into subjects (name_subject)
values
    ('English'),
    ('Math'),
    ('PE');