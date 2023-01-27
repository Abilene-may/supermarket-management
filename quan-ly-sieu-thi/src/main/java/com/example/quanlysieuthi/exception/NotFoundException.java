package com.example.quanlysieuthi.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class NotFoundException extends RuntimeException {
    private String error;
    private Integer HttpStatus;

    public NotFoundException(String error, Integer httpStatus) {
        super(error);
        HttpStatus = httpStatus;
    }
}
