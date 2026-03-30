package com.vannanamkh.springhomework3.service.impl;

import com.vannanamkh.springhomework3.exception.CustomValidationException;
import com.vannanamkh.springhomework3.exception.EmptyDataException;
import com.vannanamkh.springhomework3.exception.NotFoundExceptionHandler;
import com.vannanamkh.springhomework3.model.Attendees;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.AttendeeRequest;
import com.vannanamkh.springhomework3.repository.AttendeeRepository;
import com.vannanamkh.springhomework3.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImp implements AttendeeService {

    private final AttendeeRepository attendeeRepository ;

    @Override
    public List<Attendees> getAllAttendee(int page, int size) {
        Map<String, String> errors = new HashMap<>();

        if (page <= 0) {
            errors.put("page", "Page number must be greater than 0");
        }

        if (size <= 0) {
            errors.put("size", "Size number must be greater than 0");
        }
        if (!errors.isEmpty()) {
            throw new CustomValidationException(errors);
        }
        return attendeeRepository.getAllAttendee(page ,size);
    }

    @Override
    public Attendees getAttendeeByUserId(Long id) {
        Attendees attendees = attendeeRepository.getAttendeeByUserId(id);

        if(attendees == null) {
            throw new NotFoundExceptionHandler("User with ID " + id + " not found");
        }

        return attendeeRepository.getAttendeeByUserId(id);

    }

    @Override
    public Attendees postAttendee(AttendeeRequest attendeeRequest) {

        String name = attendeeRequest.getAttendeeName();
        String email = attendeeRequest.getEmail();
        List<Attendees> attendees = attendeeRepository.getAllAttendeeItem();
        Map<String, String> errors = new HashMap<>();

        for(Attendees a : attendees){
            if(name != null && name.equalsIgnoreCase(a.getAttendeeName())){
                errors.put("Attendee", "This Attendee name is already in use");
            }
            if(email != null  && email.equalsIgnoreCase(a.getEmail())){
                errors.put("email" , "This email is already is use") ;
            }
            if (!errors.isEmpty()) {
                throw new CustomValidationException(errors);
            }
        }
        return attendeeRepository.createAttendee(attendeeRequest) ;
    }

    @Override
    public Attendees updateAttendee(Long id, AttendeeRequest attendeeRequest) {

        Attendees attendees = attendeeRepository.getAttendeeByUserId(id);
        if(attendees == null) {
            throw new NotFoundExceptionHandler("User with ID " + id + " not found");
        }
        String name = attendeeRequest.getAttendeeName();
        String email = attendeeRequest.getEmail();
        List<Attendees> attendeesItem = attendeeRepository.getAllAttendeeItem();
        Map<String, String> errors = new HashMap<>();
        for(Attendees a : attendeesItem){
            if(name != null && name.equalsIgnoreCase(a.getAttendeeName())){
                errors.put("Attendee", "This Attendee name is already in use");
            }
            if(email != null  && email.equalsIgnoreCase(a.getEmail())){
                errors.put("email" , "This email is already is use") ;
            }
            if (!errors.isEmpty()) {
                throw new CustomValidationException(errors);
            }
        }

        return attendeeRepository.updateAttendee(id , attendeeRequest);
    }

    @Override
    public Attendees deleteAttendee(Long id) {
        Attendees attendees = attendeeRepository.getAttendeeByUserId(id);
        if(attendees == null) {
            throw new NotFoundExceptionHandler("User with ID " + id + " not found");
        }
        return attendeeRepository.deleteAttendee(id);
    }
}
