package com.rupesh.util.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse<T> {

    private String message;
    private HttpStatus status;
    private int statusCode;
    private LocalDateTime timeStamp;
    private T data;

}
