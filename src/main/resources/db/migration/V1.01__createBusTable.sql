CREATE TABLE bus
(
    bus_id          int(11) NOT NULL AUTO_INCREMENT,
    route_id        int(11),
    license_plate   varchar(255) DEFAULT NULL,
    number_of_seats int(11)      DEFAULT NULL,
    model           varchar(255) DEFAULT NULL,
    year            int(4)      DEFAULT NULL,
    PRIMARY KEY (bus_id),
    FOREIGN KEY (route_id) REFERENCES route (route_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);