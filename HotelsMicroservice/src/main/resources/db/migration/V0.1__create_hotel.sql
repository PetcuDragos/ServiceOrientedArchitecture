CREATE TABLE hotel
(
    id              INT PRIMARY KEY,
    name            VARCHAR(50),
    city            VARCHAR(50),
    price_per_night FLOAT,
    places_left     INT
);

INSERT INTO hotel
values (1, 'Margareta', 'Bistrita', 10, 10);