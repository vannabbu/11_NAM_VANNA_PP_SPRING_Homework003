package com.vannanamkh.springhomework3.repository;

import com.vannanamkh.springhomework3.model.Attendees;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EventAttendeeRepository {
    @Results(id = "mapper" , value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName" , column = "attendee_name"),
    })
    @Select("""
    SELECT
        a.attendee_id,
        a.attendee_name,
        a.email
    FROM attendees a
    JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
    WHERE ea.event_id = #{eventId};
    """)
    List<Attendees> findAttendeeByEventId(Long eventId);

    @ResultMap("mapper")
    @Select("insert into event_attendee values (#{attendeeId} ,#{eventId})")
    void saveEventAttendee(Long attendeeId ,Long eventId);

    @ResultMap("mapper")
    @Select("update event_attendee set event_id = #{eventId} ,attendee_id = #{attendeeId}")
    void updateEventAttendee(Long eventId,Long attendeeId);

    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteByEventId(@Param("eventId") Long eventId);

}
