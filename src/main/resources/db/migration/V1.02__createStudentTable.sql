CREATE TABLE student
(
    student_id int(11) NOT NULL AUTO_INCREMENT,
    route_id   int(11) DEFAULT NULL,
    bus_id int(11) DEFAULT NULL,
    first_name varchar(255) DEFAULT NULL,
    last_name  varchar(255) DEFAULT NULL,
    address    varchar(255) DEFAULT NULL,
    PRIMARY KEY (student_id),
    FOREIGN KEY (route_id) REFERENCES route (route_id)
        ON UPDATE CASCADE,
    FOREIGN KEY (bus_id) REFERENCES bus(bus_id)

);