CREATE TABLE assistant
(
    assistant_id int(11) NOT NULL AUTO_INCREMENT,
    route_id     int(11) NOT NULL,
    bus_id       int(11) NOT NULL,
    first_name   varchar(255) DEFAULT NULL,
    last_name    varchar(255) DEFAULT NULL,
    PRIMARY KEY (assistant_id),
    FOREIGN KEY (route_id) REFERENCES route (route_id)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (bus_id) REFERENCES bus (bus_id)
);
