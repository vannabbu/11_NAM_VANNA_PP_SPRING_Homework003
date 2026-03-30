package com.vannanamkh.springhomework3.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@AllArgsConstructor
@Builder
public class ErrorResponse{

    String instance;
    int status;
    String title;
    String type ;
    LocalDateTime timestamp;
    Map<String, String> errors;
}
