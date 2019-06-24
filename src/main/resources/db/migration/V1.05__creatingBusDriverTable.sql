CREATE TABLE bus_driver
(
    driver_id  int(11) NOT NULL AUTO_INCREMENT,
    bus_id     int(11)      DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name  varchar(255) DEFAULT NULL,
    license_id int(11) NOT NULL,
    PRIMARY KEY (driver_id),
    FOREIGN KEY (bus_id) REFERENCES bus (bus_id),
    FOREIGN KEY (license_id) REFERENCES driving_license (license_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);