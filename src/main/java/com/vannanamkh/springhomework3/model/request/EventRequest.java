package com.vannanamkh.springhomework3.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @Schema(description = "The name of the event", example = "Tech Conference 2026", maxLength = 100)
    @Size(max = 100)
    private String eventName;

    @Schema(description = "Date of the event", example = "2026-05-20")
    private Date eventDate;

    @Schema(description = "ID of the venue", example = "1")
    private Long venueId;

    @Schema(
            description = "List of attendee IDs participating in the event",
            example = "[1, 2, 3]",
            minimum = "1"
    )
    private List<Long> attendeeIds;
}
