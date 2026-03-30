package com.vannanamkh.springhomework3.comtroller;


import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.VenueRequest;
import com.vannanamkh.springhomework3.model.response.ApiResponse;
import com.vannanamkh.springhomework3.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private  final VenueService venueService ;
    @GetMapping
    @Operation(summary = "Venues")

    public ResponseEntity<ApiResponse<List<Venues>>> getAllVenue(
           @RequestParam @Positive int page,
            @RequestParam @Positive int size) {

        List<Venues> venues = venueService.getAllVenue(page, size);

        ApiResponse<List<Venues>> apiResponse = ApiResponse.<List<Venues>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully")
                .status("Ok")
                .payload(venues)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{venues_id}")
    public ResponseEntity<ApiResponse<Venues>> getVenuesById(@PathVariable("venues_id") @Positive Long Id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .timestamp(Instant.now())
                        .message("Get user with ID " + Id + " successfully")
                        .status("Ok")
                        .payload(venueService.getVenueByUserId(Id))
                        .build()
        );
    }

    @PostMapping
    public  ResponseEntity<ApiResponse<Venues>> postVenueById(@Valid  @RequestBody VenueRequest venueRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .timestamp(Instant.now())
                        .message("Created venue successfully")
                        .status("Create")
                        .payload(venueService.postVenue(venueRequest))
                        .build()
        );
    }

    @PutMapping("/{venue_id}")
    public ResponseEntity<ApiResponse<Venues>> updateVenue(@Valid  @PathVariable("venue_id") Long id , @RequestBody VenueRequest venueRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .timestamp(Instant.now())
                        .message("Update venue with id ="+id+" successfully")
                        .status("ok")
                        .payload(venueService.updateVenue(id,venueRequest))
                        .build()
        );
    }

    @DeleteMapping("/{venue_id}")
    public  ResponseEntity<ApiResponse<Venues>> postVenueById(  @PathVariable("venue_id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venues>builder()
                        .timestamp(Instant.now())
                        .message("Deleted venue with id ="+id+" successfully")
                        .payload(venueService.deleteVenue(id))
                        .status("ok")
                        .build()
        );
    }

}
