package com.vannanamkh.springhomework3.service;

import com.vannanamkh.springhomework3.model.Attendees;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.AttendeeRequest;
import com.vannanamkh.springhomework3.model.request.VenueRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendees> getAllAttendee(int page, int size);
    Attendees getAttendeeByUserId(Long id);
    Attendees postAttendee(AttendeeRequest attendeeRequest);
    Attendees updateAttendee(Long id , AttendeeRequest attendeeRequest);
    Attendees deleteAttendee(Long id);
}
