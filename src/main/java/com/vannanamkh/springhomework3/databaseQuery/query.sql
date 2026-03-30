create table venues(
                       venue_id serial primary key  ,
                       venue_name  varchar(99) ,
                       location varchar(90)
)

select * from venues

SELECT * FROM venues WHERE venue_name IS NULL;

SELECT * FROM venues


create table attendees
(
    attendee_id serial primary key ,
    attendee_name varchar(90) ,
    email varchar(90)
)

CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(255),
                        event_date DATE,
                        venue_id INT
);

create table event_attendee(
                               attendee_id int  ,
                               event_id int
)

