package com.vannanamkh.springhomework3.service;

import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.VenueRequest;

import java.util.List;

public interface VenueService {

        List<Venues> getAllVenue(int page, int size);
        Venues getVenueByUserId(Long id);
        Venues postVenue(VenueRequest venueRequest);
        Venues updateVenue(Long id , VenueRequest venueRequest);
        Venues deleteVenue(Long id);

}
