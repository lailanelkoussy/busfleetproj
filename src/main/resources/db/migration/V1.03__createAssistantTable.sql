CREATE TABLE assistant
(
    assistant_id int(11) NOT NULL AUTO_INCREMENT,
    bus_id       int(11) DEFAULT NULL,
    first_name   varchar(255) DEFAULT NULL,
    last_name    varchar(255) DEFAULT NULL,
    PRIMARY KEY (assistant_id),
    FOREIGN KEY (bus_id) REFERENCES bus (bus_id)
);
