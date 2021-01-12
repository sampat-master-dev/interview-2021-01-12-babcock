INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (1, 'AA07ZZA', 'SMALL', 'Toyota', 'Avensis', 'Petrol');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (2, 'AA07ZZB', 'SMALL', 'Toyota', 'Avensis', 'Diesel');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (10, 'BB07YYB', 'ESTATE', 'Nissan', 'Qashqai', 'Petrol');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (11, 'BB07YYB', 'ESTATE', 'Nissan', 'Qashqai', 'Diesel');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (20, 'CC01XXA', 'SMALL', 'Toyota', 'Aygo', 'Petrol');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (21, 'CC01XXB', 'SMALL', 'Toyota', 'Aygo', 'Diesel');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (30, 'DD07WWA', 'VAN', 'Toyota', 'Proace', 'Petrol');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (31, 'DD07WWB', 'VAN', 'Toyota', 'Proace', 'Diesel');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (40, 'EE07VVA', 'VAN', 'Volkswagon', 'California', 'Petrol');
INSERT INTO vehicle (id, registration_number, category, make, model, fuel_type) VALUES (41, 'EE07VVB', 'VAN', 'Volkswagon', 'California', 'Diesel');

INSERT INTO customer (id, name, customer_type) VALUES (1, 'Fred Priest', 'PRIVATE');
INSERT INTO customer (id, name, customer_type) VALUES (2, 'Joe Garlick', 'PRIVATE');
INSERT INTO customer (id, name, customer_type) VALUES (3, 'Not Hired', 'PRIVATE');
INSERT INTO customer (id, name, customer_type) VALUES (4, 'Hired InPast', 'PRIVATE');
INSERT INTO customer (id, name, customer_type) VALUES (5, 'Google', 'BUSINESS');
INSERT INTO customer (id, name, customer_type) VALUES (6, 'Apple', 'BUSINESS');
INSERT INTO customer (id, name, customer_type) VALUES (7, 'Microsoft', 'BUSINESS');

INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (1,  1, 1, DATE('2020-09-01'), DATE('2020-10-02'));
INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (2, 11, 2, DATE('2021-01-11'), DATE('2020-01-13'));
INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (3, 21, 3, DATE('2021-01-11'), DATE('2020-01-14'));
INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (4, 31, 4, DATE('2021-01-11'), DATE('2020-01-15'));
INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (5, 41, 5, DATE('2021-01-11'), DATE('2020-01-16'));
INSERT INTO vehicle_hire (id, vehicle_id, customer_id, rental_start_date, rental_end_date) VALUES (6, 41, 6, DATE('2021-01-11'), DATE('2020-01-30'));

INSERT INTO vehicle_category_price (category, price) VALUES ('Small car', '25.0');
INSERT INTO vehicle_category_price (category, price) VALUES ('Estate car', '35.0');
INSERT INTO vehicle_category_price (category, price) VALUES ('Van', '50.0');
