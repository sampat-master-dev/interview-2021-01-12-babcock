DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS vehicle_category_price;
DROP TABLE IF EXISTS vehicle_hire;


CREATE TABLE vehicle (
    id                  LONG        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(50) NOT NULL,
    category            VARCHAR(16) NOT NULL,
    make                VARCHAR(16) NOT NULL,
    model               VARCHAR(16) NOT NULL,
    fuel_type           VARCHAR(16) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle_category_price (
    category VARCHAR(16)   NOT NULL,
    price    DECIMAL(2, 0) NOT NULL,
    PRIMARY KEY (category)
);

CREATE TABLE IF NOT EXISTS customer (
    id            LONG         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    customer_type VARCHAR(32)  NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle_hire (
    id                LONG NOT NULL AUTO_INCREMENT PRIMARY KEY,
    vehicle_id        LONG NOT NULL,
    customer_id       LONG NOT NULL,
    rental_start_date DATE NOT NULL,
    rental_end_date   DATE NOT NULL
);
