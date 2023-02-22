CREATE TABLE hotel
(
    id              INT PRIMARY KEY,
    name            VARCHAR(50),
    city            VARCHAR(50),
    price_per_night FLOAT,
    places_left     INT
);

INSERT INTO hotel
values (1, 'Claridge', 'London', 10, 10),
       (2, 'The Savoy', 'London', 30, 15),
       (3, 'Mercure Wien Westbahnhof', 'Vienna', 100, 30),
       (4, 'St Christopher', 'Vienna', 80, 10),
       (5, 'Leonardo Hotel', 'Berlin', 60, 20),
       (6, 'Hotel Amano Grand Central', 'Berlin', 60, 20),
       (7, 'Campanile Paris Bercy Village', 'Paris', 100, 20),
       (8, 'Hotel Lutetia', 'Paris', 100, 20),
       (9, 'One Shot Arago 257', 'Barcelona', 80, 10),
       (10, 'Occidental Barcelona 1929', 'Barcelona', 80, 10);