package com.vannanamkh.springhomework3.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @Schema(
            description = "Name of the attendee (letters and spaces only)",
            example = "Vanna Nam",
            minLength = 1,
            maxLength = 50,
            pattern = "^[a-zA-Z\\s]+$"
    )
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String attendeeName;

    @Schema(
            description = "Email address",
            example = "vanna@example.com",
            minLength = 0,
            maxLength = 50,
            pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;
}
