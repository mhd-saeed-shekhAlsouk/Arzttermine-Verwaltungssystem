CREATE DATABASE appointmentSystem;

USE appointmentSystem;

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    dob DATE,
    email VARCHAR(255) UNIQUE,
    gender VARCHAR(50),
    isActive BOOLEAN
);


CREATE TABLE doctors (
    id INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    dob DATE,
    email VARCHAR(255) UNIQUE,
    gender VARCHAR(50),
    isActive BOOLEAN,

);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date VARCHAR(50),
    patientId INT,
    doctorId INT,
    time VARCHAR(50),
    FOREIGN KEY (patientId) REFERENCES patients(id),
    FOREIGN KEY (doctorId) REFERENCES doctors(id)
);



-- Insert patients
INSERT INTO patients (id, firstName, lastName, username, password, dob, email, gender, isActive)
VALUES 
('Maria', 'Schmidt', 'maria', '1234', '1980-03-12', 'maria.schmidt@example.com', 'Female', true, true);

-- Insert doctors
INSERT INTO doctors (id, firstName, lastName, username, password, dob, email, gender, isActive)
VALUES 
(101, 'Hans', 'Schmidt', 'hans', '1234', '1980-03-12', 'hans.schmidt@example.com', 'Male', true),
(102, 'Klara', 'Müller', 'klara', '1234', '1985-06-23', 'klara.mueller@example.com', 'Female', true),
(103, 'Johann', 'Bauer', 'johann', '1234', '1975-11-30', 'johann.bauer@example.com', 'Male', true);




