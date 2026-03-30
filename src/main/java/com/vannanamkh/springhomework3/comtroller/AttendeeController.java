package com.vannanamkh.springhomework3.comtroller;

import com.vannanamkh.springhomework3.model.Attendees;
import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.AttendeeRequest;
import com.vannanamkh.springhomework3.model.request.VenueRequest;
import com.vannanamkh.springhomework3.model.response.ApiResponse;
import com.vannanamkh.springhomework3.service.AttendeeService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendee")
@RequiredArgsConstructor
public class AttendeeController {
    private  final AttendeeService attendeeService ;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendees>>> getAllVenue(
            @RequestParam @Positive int page,
            @RequestParam @Positive int size) {

        List<Attendees> attendees = attendeeService.getAllAttendee(page, size);

        ApiResponse<List<Attendees>> apiResponse = ApiResponse.<List<Attendees>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendee successfully")
                .status("Ok")
                .payload(attendees)
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{attendee_id}")
    public ResponseEntity<ApiResponse<Attendees>> getAttendeeById(@PathVariable("attendee_id") @Positive Long Id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendees>builder()
                        .timestamp(Instant.now())
                        .message("Get user with ID " + Id + " successfully")
                        .status("Ok")
                        .payload(attendeeService.getAttendeeByUserId(Id))
                        .build()
        );
    }

    @PostMapping
    public  ResponseEntity<ApiResponse<Attendees>> postAttendeeById(@RequestBody AttendeeRequest attendeeRequest){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendees>builder()
                        .timestamp(Instant.now())
                        .message("Created attendee successfully")
                        .status("Create")
                        .payload(attendeeService.postAttendee(attendeeRequest))
                        .build()
        );
    }

    @PutMapping("/{attendee_id}")
    public ResponseEntity<ApiResponse<Attendees>> updateAttendee(@PathVariable("attendee_id") Long id , @RequestBody AttendeeRequest attendeeRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendees>builder()
                        .timestamp(Instant.now())
                        .message("Update attendee with id ="+id+" successfully")
                        .status("ok")
                        .payload(attendeeService.updateAttendee(id,attendeeRequest))
                        .build()
        );
    }

    @DeleteMapping("/{attendee_id}")
    public  ResponseEntity<ApiResponse<Attendees>> postAttendeeById(@PathVariable("attendee_id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendees>builder()
                        .timestamp(Instant.now())
                        .message("Deleted attendee with id ="+id+" successfully")
                        .status("ok")
                        .payload(attendeeService.deleteAttendee(id))
                        .build()
        );
    }
}
