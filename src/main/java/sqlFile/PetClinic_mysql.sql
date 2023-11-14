CREATE DATABASE `Pet_Clinic`;

USE `Pet_Clinic`;

-- DROP DATABASE `Pet_Clinic`;

-- SELECT u.*, dl.*, ds.* 
-- FROM pet_clinic.User AS u 
-- INNER JOIN pet_clinic.DoctorLevel AS dl 
-- ON u.user_id = dl.user_id
-- INNER JOIN pet_clinic.doctorschedual AS ds
-- ON u.user_id = ds.user_id
-- WHERE u.user_id = 2;

CREATE TABLE `Role` (
    `role_id` INT PRIMARY KEY NOT NULL,
    `role_name` VARCHAR(10) NOT NULL
);
insert into `Role`(`role_id`, `role_name`)
values(1 , "Admin"), (2, "Doctor"), (3, "Client");

CREATE TABLE `User` (
    `user_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `role_id` INT NOT NULL, 
    FOREIGN KEY (`role_id`) REFERENCES `Role`(`role_id`),
    `full_name` VARCHAR(50) NOT NULL,
    `gender` INT,
    `dob` DATE,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `address` VARCHAR(255) NOT NULL,
    `phone_number` CHAR(10) NOT NULL,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `status` INT NOT NULL
);
insert into `User`
(`role_id`, `full_name`, `gender`, `dob`,
`email`, `address`, `phone_number`, `username`,
`password`, `status`)
values(1, "Doan Hoang Anh", 0, "2003-04-08", "anh.dh.1876@gmail.com", 
"54 Le Thanh Nghi", "0123456789", "admin", "123456", 1),
(2, "Nguyen Thu Minh", 1, "2003-11-25", "minh.nt.1889@gmail.com",
"54 Le Thanh Nghi", "0123456789", "doctor", "123456", 1),
(3, "Vu Tuan Anh", 0, "2003-01-01", "minh.vt.1888@gmail.com",
"54 Le Thanh Nghi", "0123456789", "client", "123456", 1);

CREATE TABLE `DoctorLevel` (
    `doctor_level_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `user_id` INT, 
    FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`),
    `title` VARCHAR(50) NOT NULL,
    `update_date` DATE
);
insert into `DoctorLevel`(`user_id`, `title`)
value(2, "PhD");

CREATE TABLE `DoctorSchedual` (
    `doctor_schedual_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `user_id` INT  NOT NULL, 
    FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`),
    `doctor_schedual_date` DATE,
    `doctor_schedual_time` VARCHAR(50)
);
insert into `DoctorSchedual`(`user_id`, `doctor_schedual_date`, `doctor_schedual_time`)
value(2, "2022-12-07", "Morning");

CREATE TABLE `Pet` (
    `pet_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `user_id` INT NOT NULL, 
    FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`),
    `pet_type` VARCHAR(50) NOT NULL,
    `pet_name` VARCHAR(50),
    `pet_gender` INT,
    `pet_weight` FLOAT,
    `pet_age` INT
);
insert into `Pet`(`user_id`, `pet_type`, `pet_name`,
`pet_gender`, `pet_weight`, `pet_age`)
values(3, "Dog", "KuKu", 1, 18.9, 10);

CREATE TABLE `Service` (
    `service_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `service_name` VARCHAR(50) NOT NULL,
    `service_type` VARCHAR(50) NOT NULL,
    `service_price` DECIMAL(10, 2)
);
insert into `Service`(`service_name`, `service_type`, `service_price`)
value("Bath & Brush", "Spa", "10.02"),
("Bath & Full Haircut", "Spa", "15.02"),
("Puppy Bath & Brush", "Spa", "9.02"),
("Puppy Bath & Full Haircut", "Spa", "11.02"),
("Ultrasound", "Health Check", "20.00"),
("Sterilize", "Health Check", "30.00");

CREATE TABLE `Booking` (
    `booking_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `service_id` INT  NOT NULL, 
    FOREIGN KEY(`service_id`) REFERENCES `Service`(`service_id`),
    `user_id` INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES `User`(`user_id`),
    `pet_id` INT  NOT NULL, 
    FOREIGN KEY(`pet_id`) REFERENCES `Pet`(`pet_id`),
    `booking_date` DATE NOT NULL,
    `booking_time` VARCHAR(10) NOT NULL,
    `update_date` DATE,
    `price` DECIMAL(10, 2)
);
insert into `Booking`(`service_id`, `user_id`, `pet_id`, `booking_date`, `booking_time`, `price`)
values(1, 3, 1, "2022-12-07", "Morning", "10.02");

CREATE TABLE `Prescription` (
    `prescription_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `prescription_date` DATE
);
insert into `Prescription`(`prescription_date`)
value("2022-12-07");

CREATE TABLE `Medication` (
    `medication_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `medication_name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(50),
    `price` DECIMAL(10, 2)
);
insert into `Medication`(`medication_name`, `description`, `price`)
values("Dr-Namda Ketocho Well Shampoo", "Bath oil is used to fight fungi, bacteria", 40.02),
("F22/TYLANGEN", "Treatment of respiratory, genitourinary, urinary", 49.02),
("SU27/BUTAB12", "Treat weakness, stunting, anemia", 48.02),
("Phar-Combido", "Synthetic antibiotics", 47.02),
("Pharcado", "Remove tapeworms and roundworms", 46.22);

CREATE TABLE `PrescriptionDetail` (
    `medication_manage_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `prescription_id` INT,
    FOREIGN KEY(`prescription_id`) REFERENCES `Prescription`(`prescription_id`),
    `medication_id` INT,
    FOREIGN KEY(`medication_id`) REFERENCES `Medication`(`medication_id`)
);
insert into `PrescriptionDetail`(`prescription_id`,  `medication_id`)
values(1, 5);
