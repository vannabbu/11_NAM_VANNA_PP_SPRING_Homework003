package com.vannanamkh.springhomework3.service.impl;
import com.vannanamkh.springhomework3.exception.CustomValidationException;
import com.vannanamkh.springhomework3.exception.NotFoundExceptionHandler;
import com.vannanamkh.springhomework3.model.Attendees;
import com.vannanamkh.springhomework3.model.Event;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.EventRequest;
import com.vannanamkh.springhomework3.repository.AttendeeRepository;
import com.vannanamkh.springhomework3.repository.EventAttendeeRepository;
import com.vannanamkh.springhomework3.repository.EventRepository;
import com.vannanamkh.springhomework3.repository.VenueRepository;
import com.vannanamkh.springhomework3.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {
        private final EventRepository eventRepository ;
        private final EventAttendeeRepository eventAttendeeRepository ;
    private final AttendeeRepository attendeeRepository;
    private final VenueRepository venueRepository;

    @Override
        public List<Event> getAllEvent(int page, int size) {

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


            return eventRepository.getAllEvents(page, size);
        }

        @Override
        public Event getEventById(Long id) {

            Event event = eventRepository.getEventById(id);

            if (event == null) {
                throw new NotFoundExceptionHandler("Event with ID " + id + " not found");
            }

            return event;
        }

        @Override
        public Event postEvent(EventRequest eventRequest) {

            String name = eventRequest.getEventName();
            List<Event> events = eventRepository.getAllEventsItem();

            for (Event e : events) {
                if (name != null && name.equalsIgnoreCase(e.getEventName())) {
                    throw new CustomValidationException("eventName", "This event name is already in use");
                }
            }
            //add number
            Event event = eventRepository.createEvent(eventRequest);
            for(Long attendeeId : eventRequest.getAttendeeIds()){
                eventAttendeeRepository.saveEventAttendee(attendeeId , event.getEventId());
            }
            return eventRepository.createEvent(eventRequest);
        }

    @Override
    public Event updateEvent(Long id, EventRequest eventRequest) {

        Event existingEvent = eventRepository.getEventById(id);

        if (existingEvent == null) {
            throw new NotFoundExceptionHandler("Event with ID " + id + " not found");
        }

        Map<String, String> errors = new HashMap<>();

        if (eventRequest.getVenueId() != null) {
            Venues venue = venueRepository.getVenueByVenueId(eventRequest.getVenueId());
            if (venue == null) {
                errors.put("venueId", "The specified venue does not exist.");
            }
        }

        if (eventRequest.getAttendeeIds() != null && !eventRequest.getAttendeeIds().isEmpty()) {
            for (Long attendeeId : eventRequest.getAttendeeIds()) {
                Attendees attendee = attendeeRepository.getAttendeeByUserId(Long.valueOf(attendeeId));
                if (attendee == null) {
                    errors.put("attendeeIds", "Attendee ID " + attendeeId + " does not exist.");
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new CustomValidationException(errors);
        }

        //add

        eventAttendeeRepository.deleteByEventId(id);
        for(Long attendeeId : eventRequest.getAttendeeIds()){
            eventAttendeeRepository.saveEventAttendee(attendeeId , id);
        }
        return eventRepository.updateEvent(id, eventRequest);
    }

        @Override
        public Event deleteEvent(Long id) {

            Event event = eventRepository.getEventById(id);

            if (event == null) {
                throw new CustomValidationException("id", "Event with ID " + id + " not found");
            }

            return eventRepository.deleteEvent(id);
        }
}

