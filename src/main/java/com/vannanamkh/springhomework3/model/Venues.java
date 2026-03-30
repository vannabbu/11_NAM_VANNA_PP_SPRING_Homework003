package com.vannanamkh.springhomework3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venues {
    private Long venueId ;
    private String venueName ;
    private String location;
    public String getVenueName() {
        return venueName;
    }
}
