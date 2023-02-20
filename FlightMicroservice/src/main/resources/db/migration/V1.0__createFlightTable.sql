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
values (1, 'Cluj', 'Bistrita', 20, 15, 100),
       (2, 'Cluj', 'Vienna', 60, 150, 15);