package com.vannanamkh.springhomework3.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    @Schema(
            description = "Name of the venue",
            example = "Phnom Penh Cultural Center",
            maxLength = 100
    )
    @Size(max = 100)
    private String venueName;

    @Schema(
            description = "Physical address or coordinates of the venue",
            example = "St. 271, Phnom Penh, Cambodia",
            maxLength = 150
    )
    @Size(max = 150)
    private String location;
    public String getVenueName() {
        return venueName;
    }
}

