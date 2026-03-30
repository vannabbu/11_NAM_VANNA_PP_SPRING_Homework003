package com.vannanamkh.springhomework3.comtroller;
import com.vannanamkh.springhomework3.model.Event;
import com.vannanamkh.springhomework3.model.request.EventRequest;
import com.vannanamkh.springhomework3.model.response.ApiResponse;
import com.vannanamkh.springhomework3.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Events")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvent(
            @RequestParam @Positive int page,
            @RequestParam @Positive int size) {

        List<Event> events = eventService.getAllEvent(page, size);

        ApiResponse<List<Event>> apiResponse = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully")
                .status("Ok")
                .payload(events)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(
            @PathVariable("event_id") @Positive Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Get event with ID " + id + " successfully")
                        .status("Ok")
                        .payload(eventService.getEventById(id))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid  @RequestBody EventRequest eventRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Created event successfully")
                        .status("Create")
                        .payload(eventService.postEvent(eventRequest))
                        .build()
        );
    }

    @PutMapping("/{event_id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @Valid    @PathVariable("event_id") Long id,
            @RequestBody EventRequest eventRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Updated event with id = " + id + " successfully")
                        .status("Ok")
                        .payload(eventService.updateEvent(id, eventRequest))
                        .build()
        );
    }

    @DeleteMapping("/{event_id}")
    public ResponseEntity<ApiResponse<Event>> deleteEvent(
            @Valid  @PathVariable("event_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Deleted event with id = " + id + " successfully")
                        .status("Ok")
                        .payload(eventService.deleteEvent(id))
                        .build()
        );
    }
}