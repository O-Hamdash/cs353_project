CREATE DATABASE gobookingdb
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    OWNER = postgres;

-- Note: In PostgreSQL, the USE statement is not needed since each query runs in its own transaction and can reference any database,
-- as long as the user has the necessary permissions to access it.

-- If you want to set the default database


CREATE TABLE "user"
(
    user_id   SERIAL       NOT NULL,
    name      varchar(255) NOT NULL,
    surname   varchar(255) NOT NULL,
    email     varchar(255) NOT NULL,
    password  varchar(255) NOT NULL,
    birth_date timestamp    NOT NULL,
    role      varchar(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE "admin"
(
    user_id    int          NOT NULL,
    admin_role varchar(255),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES "user" (user_id)
        ON DELETE CASCADE
);

CREATE TABLE "app_user"
(
    user_id                int       NOT NULL,
    balance                int,
    city                   varchar(255),
    tax_number             varchar(255),
    registration_date      timestamp NOT NULL,
    is_blocked             boolean   NOT NULL,
    is_banned_from_booking boolean   NOT NULL,
    is_banned_from_posting boolean   NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES "user" (user_id)
        ON DELETE CASCADE
);

CREATE TABLE property
(
    property_id     SERIAL PRIMARY KEY NOT NULL,
    title           varchar(255),
    status          varchar(255),
    floor           int,
    is_furnished    BOOLEAN            NOT NULL,
    description     varchar(255),
    max_guests      int,
    price_per_night float,
    owner_id        int                NOT NULL,
    location_id     int                NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES "user" (user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (location_id) REFERENCES location (location_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE room
(
    property_id     int NOT NULL,
    room_dimensions int,
    PRIMARY KEY (property_id),
    FOREIGN KEY (property_id) REFERENCES property (property_id)
        ON DELETE CASCADE
);

CREATE TABLE house
(
    property_id      int NOT NULL,
    number_of_rooms  int,
    house_dimensions int,
    PRIMARY KEY (property_id),
    FOREIGN KEY (property_id) REFERENCES property (property_id) ON DELETE CASCADE
);

CREATE TABLE location
(
    location_id        SERIAL NOT NULL,
    city               varchar(255),
    neighborhood       varchar(255),
    road               varchar(255),
    street             varchar(255),
    longitude_latitude varchar(40),
    description        varchar(255),
    PRIMARY KEY (location_id)
);

CREATE TABLE booking
(
    booking_id  SERIAL PRIMARY KEY NOT NULL,
    start_date  TIMESTAMP          NOT NULL,
    end_date    TIMESTAMP,
    status      varchar(255),
    booker_id   int                NOT NULL,
    property_id int                NOT NULL,
    FOREIGN KEY (booker_id) REFERENCES App_user (user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES property (property_id)
        ON DELETE CASCADE
);

CREATE TABLE review
(
    review_id    SERIAL PRIMARY KEY NOT NULL,
    reviewer_id  int                NOT NULL,
    rating       int                NOT NULL,
    review_title varchar(255)       NOT NULL,
    review_body  varchar(255),
    booking_id   int                NOT NULL,
    FOREIGN KEY (reviewer_id) REFERENCES App_user (user_id)
        ON DELETE CASCADE,
    FOREIGN KEY (booking_id) REFERENCES booking (booking_id)
        ON DELETE CASCADE
);

CREATE TABLE review_for_traveller
(
    review_id    int NOT NULL,
    traveller_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) REFERENCES review (review_id)
        ON DELETE CASCADE,
    FOREIGN KEY (traveller_id) REFERENCES App_user (user_id)
        ON DELETE CASCADE
);

CREATE TABLE review_for_homeowner
(
    review_id    int NOT NULL,
    homeowner_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) REFERENCES review (review_id)
        ON DELETE CASCADE,
    FOREIGN KEY (homeowner_id) REFERENCES App_user (user_id)
        ON DELETE CASCADE
);

CREATE TABLE review_for_property
(
    review_id   int NOT NULL,
    property_id int NOT NULL,
    PRIMARY KEY (review_id),
    FOREIGN KEY (review_id) REFERENCES review (review_id)
        ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES property (property_id)
        ON DELETE CASCADE
);

CREATE TABLE pays
(
    booking_id   int      NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    "cost"         float    NOT NULL,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (booking_id) REFERENCES booking (booking_id)
        ON DELETE CASCADE
);

CREATE TABLE service
(
    service_name varchar(255) PRIMARY KEY NOT NULL,
    property_id  int                      NOT NULL,
    FOREIGN KEY (property_id) REFERENCES property
        ON DELETE CASCADE
);


-- Assertions & additional constraints
-- no Assertion in PostgreSQL, use Rule instead

CREATE OR REPLACE RULE no_overlapping_bookings AS
    ON INSERT TO booking
    WHERE EXISTS (
            SELECT *
            FROM booking b1,
                 booking b2
            WHERE b1.booking_id <> b2.booking_id
              AND b1.property_id = b2.property_id
              AND b1.status <> 'cancelled'
              AND b2.status <> 'cancelled'
              AND ((b1.start_date <= b2.start_date AND b1.end_date > b2.start_date)
                OR (b1.start_date >= b2.start_date AND b2.end_date > b1.start_date))
        )
    DO INSTEAD NOTHING;

-- unique_user_email assertion becomes a constraint in PostgreSQL
ALTER TABLE "user" ADD CONSTRAINT unique_user_email UNIQUE (email);

-- unique_review_per_booking assertion becomes a constraint in PostgreSQL
ALTER TABLE review ADD CONSTRAINT unique_review_per_booking UNIQUE (booking_id, reviewer_id);

-- unique_property_owner assertion becomes a constraint in PostgreSQL
ALTER TABLE property ADD CONSTRAINT unique_property_owner UNIQUE (owner_id);

-- check_services assertion becomes a constraint in PostgreSQL
ALTER TABLE service ADD CONSTRAINT check_services
    CHECK (service_name IN ('Wi - Fi', 'Security',
                            'Breakfast', 'Sea View',
                            'Kitchen', 'Play Ground',
                            'Park', 'Garden', 'Organic Life'));

-- valid_booking_status assertion becomes a constraint postgresql
ALTER TABLE booking ADD CONSTRAINT valid_booking_status
    CHECK ( status IN ('blocked', 'booked', 'available'));



-- TRIGGERS & their functions
CREATE LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_reviews()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.is_blocked = true THEN
        DELETE FROM review WHERE reviewer_id = NEW.user_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_reviews_trigger
    AFTER UPDATE ON app_user
    FOR EACH ROW
EXECUTE FUNCTION delete_reviews();


CREATE OR REPLACE FUNCTION update_balances()
    RETURNS TRIGGER AS $$
BEGIN
    -- Deduct booking cost from travelers' balance
    UPDATE app_user
    SET balance = balance - (SELECT cost
                             FROM pays
                             WHERE booking_id = NEW.booking_id)
    WHERE user_id = NEW.booker_id;

    -- Add booking cost to house owner's balance
    UPDATE app_user
    SET balance = balance + (SELECT cost
                             FROM pays
                             WHERE booking_id = NEW.booking_id)
    WHERE user_id = (SELECT property.owner_id
                     FROM property
                     WHERE property.property_id = NEW.property_id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_balances
    AFTER INSERT
    ON booking
    FOR EACH ROW
EXECUTE FUNCTION update_balances();


-- VIEW

CREATE VIEW homeowner AS
SELECT app_user.*
FROM app_user
WHERE EXISTS (
              SELECT *
              FROM property
              WHERE property.owner_id = app_user.user_id
          );
