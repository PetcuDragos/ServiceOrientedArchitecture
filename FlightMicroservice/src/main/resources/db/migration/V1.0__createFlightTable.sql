create table flight
(
    id                  INT PRIMARY KEY,
    origin              VARCHAR(50),
    destination         VARCHAR(50),
    duration_in_minutes INT,
    price               FLOAT,
    places_left         INT
);

INSERT INTO Flight
values (1, 'Cluj', 'London', 20, 15, 10),
       (2, 'Cluj', 'Vienna', 60, 150, 15),
       (3, 'Bucuresti', 'Berlin', 80, 120, 10),
       (4, 'Bucuresti', 'Paris', 90, 150, 20),
       (5, 'Paris', 'Barcelona', 30, 30, 30)