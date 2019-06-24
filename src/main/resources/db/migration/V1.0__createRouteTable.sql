CREATE TABLE route
(
    route_id    int(11) NOT NULL AUTO_INCREMENT,
    area       varchar(255) DEFAULT NULL,
    start_point varchar(255) DEFAULT NULL,
    end_point   varchar(255) DEFAULT NULL,
    PRIMARY KEY (route_id)
)
#ENGINE = InnoDB
 # AUTO_INCREMENT = 1
 # DEFAULT CHARSET = utf8;
