package com.vannanamkh.springhomework3.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T> {
    private Instant timestamp;
    private String message;
    private String status ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}

