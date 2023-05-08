CREATE
DATABASE IF NOT EXISTS gobookingdb;
USE
gobookingdb;

CREATE TABLE User
(
    user_id   int          NOT NULL AUTO_INCREMENT,
    email     varchar(255) NOT NULL,
    name      varchar(255) NOT NULL,
    surname   varchar(255) NOT NULL,
    password  varchar(255) NOT NULL,
    birthdate datetime     NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE Admin
(
    user_id    int NOT NULL,
    admin_role varchar(255),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) references User
        ON DELETE CASCADE
);

CREATE TABLE App_user
(
    user_id                int      NOT NULL,
    balance                int,
    is_blocked             BOOLEAN  NOT NULL,
    is_banned_from_booking BOOLEAN  NOT NULL,
    city                   varchar(255),
    registration_date      datetime NOT NULL,
    tax_number             int,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) references User
        ON DELETE CASCADE
);

CREATE TABLE Property
(
    property_id     int     NOT NULL AUTO_INCREMENT,
    title           varchar(255),
    status          varchar(255),
    floor           int,
    is_furnished    BOOLEAN NOT NULL,
    description     varchar(255),
    max_guests      int,
    price_per_night float,
    owner_id        int     NOT NULL,
    location_id             NOT NULL,
    PRIMARY KEY (property_id),
    FOREIGN KEY (owner_id) references User
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (location_id) references Location
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Room
(
    property_id     int NOT NULL,
    room_dimensions int,
    PRIMARY KEY (property_id),
    FOREIGN KEY (property_id) references Property
        ON DELETE CASCADE
);

CREATE TABLE House
(
    property_id      int NOT NULL,
    number_of_rooms  int,
    house_dimensions int,
    PRIMARY KEY (property_id),
    FOREIGN KEY (property_id) references Property
        ON DELETE CASCADE
);

CREATE TABLE Location
(
    location_id        int NOT NULL AUTO_INCREMENT,
    city               varchar(255),
    neighborhood       varchar(255),
    road               varchar(255),
    street             varchar(255),
    longitude_latitude varchar(40),
    description        varchar(255),
    PRIMARY KEY (location_id)
);

CREATE TABLE Booking
(
    booking_id  int      NOT NULL AUTO_INCREMENT,
    start_date  datetime NOT NULL,
    end_date    datetime,
    status      varchar(255),
    booker_id   int      NOT NULL,
    property_id int      NOT NULL,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (booker_id) references App_user
        ON DELETE CASCADE,
    FOREIGN KEY (property_id) references Property
        ON DELETE CASCADE
);

CREATE TABLE Review
(
    review_id    int          NOT NULL AUTO_INCREMENT,
    reviewer_id  int          NOT NULL,
    rating       int          NOT NULL,
    review_title varchar(255) NOT NULL,
    review_body  varchar(255),
    booking_id   int          NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (reviwer_id) references App_user
        ON DELETE CASCADE,
    FOREIGN KEY (booking_id) references Booking
        ON DELETE CASCADE
);

CREATE TABLE Review_for_traveller
(
    review_id    int NOT NULL,
    traveller_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) references Review
        ON DELETE CASCADE,
    FOREIGN KEY (traveller_id) references App_user
        ON DELETE CASCADE
);

CREATE TABLE Review_for_homeowner
(
    review_id    int NOT NULL,
    homeowner_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) references Review
        ON DELETE CASCADE,
    FOREIGN KEY (homeowner_id) references App_user
        ON DELETE CASCADE
);

CREATE TABLE Review_for_property
(
    review_id   int NOT NULL,
    property_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) references Review
        ON DELETE CASCADE,
    FOREIGN KEY (property_id) references Property
        ON DELETE CASCADE
);

CREATE TABLE Pays
(
    booking_id   int      NOT NULL,
    payment_date datetime NOT NULL,
    cost         float    NOT NULL,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (booking_id) references Booking
        ON DELETE CASCADE
);

CREATE TABLE Service
(
    service_name varchar(255) NOT NULL,
    property_id  int          NOT NULL,
    PRIMARY_KEY(service_name),
    PRIMARY_KEY(property_id),
    FOREIGN KEY (property_id) references Property
        ON DELETE CASCADE
);

CREATE ASSERTION no_overlapping_bookings
    CHECK NOT EXISTS(
        SELECT *
        FROM Booking b1,
             Booking b2
        WHERE b1.booking_id <> b2.booking_id
          AND b1.property_id = b2.property_id
          AND b1.status <> 'cancelled'
          AND b2.status <> 'cancelled'
          AND ((b1.start_date <= b2.start_date AND b1.end_date > b2.start_date)
            OR (b1.start_date >= b2.start_date AND b2.end_date > b1.start_date))
    );

CREATE ASSERTION unique_user_email
    CHECK NOT EXISTS(
        SELECT u1.user_id, u2.user_id
        FROM User u1,
             User u2
        WHERE u1.user_id <> u2.user_id
          AND u1.email = u2.email
    );

CREATE ASSERTION unique_review_per_booking
    CHECK (
    NOT EXISTS(
            SELECT review_id
            FROM Review
            GROUP BY booking_id, reviewer_id
            HAVING COUNT(*) > 1
        )
    );

CREATE ASSERTION unique_property_owner
    CHECK NOT EXISTS(
        SELECT p1.property_id, p1.owner_id, p2.owner_id
        FROM Property p1,
             Property p2
        WHERE p1.property_id <> p2.property_id
          AND p1.owner_id = p2.owner_id
    );

CREATE ASSERTION check_services
    check (service_name in
           ('Wi - Fi', 'Security', 'Breakfast', 'Sea View', 'Kitchen', 'Play Ground', 'Park', 'Garden', 'Organic Life'))
    );

CREATE ASSERTION valid_booking_status
    CHECK (NOT EXISTS(SELECT 1
                      FROM Booking
                      WHERE status NOT IN ('blocked',
                                           'booked', 'available')))

CREATE TRIGGER delete_reviews
    AFTER UPDATE
    ON App_user
    FOR EACH ROW
BEGIN
    -- Check if the user is banned from booking
    IF NEW.is_blocked = true THEN
        -- Delete all reviews written by this user
    DELETE
    FROM Review
    WHERE reviewer_id = NEW.user_id;
END IF;
END;

CREATE TRIGGER update_balances
    AFTER INSERT
    ON Booking
    FOR EACH ROW
BEGIN
    -- Deduct booking cost from travelers' balance
    UPDATE App_user
    SET balance = balance - (SELECT cost
                             FROM pays
                             WHERE booking_id =
                                   NEW.booking_id)
    WHERE user_id = NEW.booker_id;
-- Add booking cost to house owner's balanceUPDATE app_user
    SET balance = balance + (SELECT cost FROM Pays WHERE booking_id =
NEW.booking_id)
WHERE user_id = (SELECT Property.user_id FROM Property
    WHERE Property.property_id = NEW.property_id);
END;

CREATE VIEW Homeowner AS
SELECT App_user.*
FROM App_user
WHERE EXISTS(
              SELECT *
              FROM Property
              WHERE Property.owner_id = App_user.user_id
          );

CREATE VIEW top25_houses AS
SELECT *, avg(R1.rating) AS avg_rating
FROM Review_for_homeowner R,
     Homeowner H,
     Review R1
WHERE H.user_id = R.user_id
GROUP BY H.user_id
ORDER BY avg_rating DESC LIMIT 25;
);