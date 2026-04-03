# Smart Clinic Management System – Database Schema (MySQL)

This document describes the database structure for the Smart Clinic Management System, including table design, relationships, and stored procedures.

## 1. Entity Relationship (ER) Overview

The system has four main entities:

1. Doctor
Creates prescriptions
Has multiple appointments
2. Patient
Books appointments
Receives prescriptions
3. Appointment
Connects Doctor ↔ Patient
One appointment can have one prescription
4. Prescription
Linked to one appointment

## 2. Database Tables
### 💠 Doctor Table
CREATE TABLE Doctor (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  speciality VARCHAR(100),
  experience INT,
  password VARCHAR(255)
);

### 💠 Patient Table
CREATE TABLE Patient (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  age INT,
  gender VARCHAR(10),
  password VARCHAR(255)
);

### 💠 Appointment Table
CREATE TABLE Appointment (
  id INT PRIMARY KEY AUTO_INCREMENT,
  doctor_id INT,
  patient_id INT,
  date DATE,
  time VARCHAR(20),
  status VARCHAR(20),
  FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
  FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

### 💠 Prescription Table
CREATE TABLE Prescription (
  id INT PRIMARY KEY AUTO_INCREMENT,
  appointment_id INT,
  doctor_id INT,
  patient_id INT,
  medicine TEXT,
  notes TEXT,
  FOREIGN KEY (appointment_id) REFERENCES Appointment(id)
);

## 3. Table Relationships
Table	Relationship	Type
Doctor → Appointment	One Doctor has many Appointments	1 → N
Patient → Appointment	One Patient has many Appointments	1 → N
Appointment → Prescription	One Appointment has one Prescription	1 → 1
Doctor → Prescription	One Doctor can issue many Prescriptions	1 → N
Patient → Prescription	One Patient can have many Prescriptions	1 → N

## 4. Stored Procedures
### 📌 Get Daily Appointment Report
CREATE PROCEDURE GetDailyAppointmentReportByDoctor()
BEGIN
  SELECT doctor_id, COUNT(*) AS total
  FROM Appointment
  WHERE date = CURDATE()
  GROUP BY doctor_id;
END;

### 📌 Doctor With Most Patients (Monthly)
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth()
BEGIN
  SELECT doctor_id, COUNT(DISTINCT patient_id) AS total
  FROM Appointment
  WHERE MONTH(date) = MONTH(CURDATE())
  GROUP BY doctor_id
  ORDER BY total DESC
  LIMIT 1;
END;

### 📌 Doctor With Most Patients (Yearly)

CREATE PROCEDURE GetDoctorWithMostPatientsByYear()
BEGIN
  SELECT doctor_id, COUNT(DISTINCT patient_id) AS total
  FROM Appointment
  WHERE YEAR(date) = YEAR(CURDATE())
  GROUP BY doctor_id
  ORDER BY total DESC
  LIMIT 1;
END;

## 5. Summary

This schema supports all required project features:

✔ Doctor management
✔ Patient registration
✔ Search functionality
✔ Appointment booking
✔ Prescription module
✔ Analytics via stored procedures
