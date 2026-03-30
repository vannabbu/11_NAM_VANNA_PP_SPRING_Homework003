package com.vannanamkh.springhomework3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId ;
    private String eventName ;
    private Date eventDate ;
    private Venues venues ;
    private List<Attendees> attendee ;
}

