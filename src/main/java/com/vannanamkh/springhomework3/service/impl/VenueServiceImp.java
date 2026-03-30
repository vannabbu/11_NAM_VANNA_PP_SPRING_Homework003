package com.vannanamkh.springhomework3.service.impl;

import com.vannanamkh.springhomework3.exception.CustomValidationException;
import com.vannanamkh.springhomework3.exception.NotFoundExceptionHandler;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.VenueRequest;
import com.vannanamkh.springhomework3.repository.VenueRepository;
import com.vannanamkh.springhomework3.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VenueServiceImp implements VenueService {
    private final VenueRepository venueRepository;

    @Override
    public List<Venues> getAllVenue(int page, int size) {

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

        return venueRepository.getAllVenues(page, size);
    }
    @Override
    public Venues getVenueByUserId(Long id) {

        Venues venue = venueRepository.getVenueByVenueId(id);

        if(venue == null) {
            throw new NotFoundExceptionHandler("User with ID " + id + " not found");
        }

        return venueRepository.getVenueByVenueId(id);
    }

    @Override
    public Venues postVenue(VenueRequest venueRequest) {

        String name = venueRequest.getVenueName();
        List<Venues> venues = venueRepository.getAllVenuesItem();

        for(Venues v : venues){
            if(name != null && name.equalsIgnoreCase(v.getVenueName())){
                throw new CustomValidationException("venueName", "This venue name is already in use");
            }
        }
        return venueRepository.createVenue(venueRequest);
    }

    @Override
    public Venues updateVenue(Long id, VenueRequest venueRequest) {

        Venues existingVenue = venueRepository.getVenueByVenueId(id);
        if (existingVenue == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + id + " not found");
        }

        String newName = venueRequest.getVenueName();
        String newLocation = venueRequest.getLocation();
        List<Venues> allVenues = venueRepository.getAllVenuesItem();
        Map<String, String> errors = new HashMap<>();

        for (Venues v : allVenues) {

            if (v.getVenueId().equals(id)) continue;

            if (newName != null && newName.equalsIgnoreCase(v.getVenueName())) {
                errors.put("venueName", "This venue name is already in use");
            }
            if (newLocation != null && newLocation.equalsIgnoreCase(v.getLocation())) {
                errors.put("location", "This location is already registered");
            }

            if (!errors.isEmpty()) {
                throw new CustomValidationException(errors);
            }
        }

        return venueRepository.updateVenue(id, venueRequest);
    }


    @Override
    public Venues deleteVenue(Long id) {
        Venues venueId = venueRepository.getVenueByVenueId(id);
        if (venueId == null) {
            throw new CustomValidationException("id", "Venue with ID " + id + " not found");
        }
        return venueRepository.deleteVenue(id);
    }
}
