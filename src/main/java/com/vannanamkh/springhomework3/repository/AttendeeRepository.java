package com.vannanamkh.springhomework3.repository;

import com.vannanamkh.springhomework3.model.Attendees;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.AttendeeRequest;
import com.vannanamkh.springhomework3.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "mapper" , value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select("SELECT * FROM attendees LIMIT #{size} OFFSET ${(page - 1) * size}")
    List<Attendees> getAllAttendee(int page , int size) ;

    @ResultMap("mapper")
    @Select("SELECT * FROM attendees")
    List<Attendees> getAllAttendeeItem();

    @ResultMap("mapper")
    @Select("SELECT * FROM attendees where attendee_id = #{id}")
    Attendees getAttendeeByUserId(Long id);

    @ResultMap("mapper")
    @Select("insert into  attendees (attendee_name , email) values (#{attendeeName} , #{email})  RETURNING *")
    Attendees createAttendee(AttendeeRequest attendeeRequest);

    @ResultMap("mapper")
    @Select("update attendees set attendee_name = #{req.attendeeName} , email = #{req.email}  where attendee_id = #{id} RETURNING *")
    Attendees updateAttendee(Long id , @Param("req") AttendeeRequest attendeeRequest);


    @ResultMap("mapper")
    @Select("delete from attendees where attendees_id = #{id}")
    Attendees deleteAttendee(Long id);

    @Select("SELECT * FROM attendees where attendees_id = #{id}")
    boolean existsById(Long id);
}
