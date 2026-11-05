CREATE DATABASE IF NOT EXISTS assignment4;
USE assignment4;

CREATE TABLE IF NOT EXISTS registrations (
    student_id VARCHAR(30) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    event_name VARCHAR(100) NOT NULL,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (student_id, event_name)
);