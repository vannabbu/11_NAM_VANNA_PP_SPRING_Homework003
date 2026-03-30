package com.vannanamkh.springhomework3.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import com.vannanamkh.springhomework3.model.Event;
import com.vannanamkh.springhomework3.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

        import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venues", column = "venue_id",
                    one = @One(select = "com.vannanamkh.springhomework3.repository.VenueRepository.getVenueByVenueId")),
            @Result(property = "attendee", column = "event_id",
                    many = @Many(select = "com.vannanamkh.springhomework3.repository.EventAttendeeRepository.findAttendeeByEventId"))
    })
    @Select("SELECT * FROM events LIMIT #{size} OFFSET ${(page - 1) * size}")
    List<Event> getAllEvents(int page, int size);

    @ResultMap("eventMapper")
    @Select("SELECT * FROM events")
    List<Event> getAllEventsItem();

    @ResultMap("eventMapper")
    @Select("SELECT * FROM events WHERE event_id = #{id}")
    Event getEventById(Long id);

    @ResultMap("eventMapper")
    @Select("""
        INSERT INTO events (event_name, event_date, venue_id)
        VALUES (#{eventName}, #{eventDate}, #{venueId})
        RETURNING *
    """)
    Event createEvent(EventRequest eventRequest);

    @ResultMap("eventMapper")
    @Select("""
        UPDATE events
        SET event_name = #{req.eventName},
            event_date = #{req.eventDate},
            venue_id = #{req.venueId}
        WHERE event_id = #{id}
        RETURNING *
    """)
    Event updateEvent(Long id, @Param("req") EventRequest eventRequest);

    @ResultMap("eventMapper")
    @Select("DELETE FROM events WHERE event_id = #{id} RETURNING *")
    Event deleteEvent(Long id);
}
