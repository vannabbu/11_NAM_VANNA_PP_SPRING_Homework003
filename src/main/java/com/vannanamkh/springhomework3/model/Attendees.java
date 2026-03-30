package com.vannanamkh.springhomework3.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendees {
    private int attendeeId  ;
    private String attendeeName ;
    private String email ;
}
