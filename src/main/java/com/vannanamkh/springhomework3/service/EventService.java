package com.vannanamkh.springhomework3.service;

import com.vannanamkh.springhomework3.model.Event;
import com.vannanamkh.springhomework3.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent(int page, int size);
    Event getEventById(Long id);
    Event postEvent(EventRequest eventRequest);
    Event updateEvent(Long id, EventRequest eventRequest);
    Event deleteEvent(Long id);
}
