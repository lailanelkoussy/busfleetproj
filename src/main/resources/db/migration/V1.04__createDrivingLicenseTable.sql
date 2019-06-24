CREATE TABLE driving_license
(
    license_id int(11) NOT NULL AUTO_INCREMENT,
    start_date date DEFAULT NULL,
    expiry_date date DEFAULT NULL,
    PRIMARY KEY (license_id)
);