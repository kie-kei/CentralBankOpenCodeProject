package ru.bluewater.centralbankrestapi.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class CbrException extends Exception {
    private final HttpStatusCode cbrStatusCode;
    public CbrException(HttpStatusCode httpStatus){
        super("cbr response with code " + httpStatus );
        cbrStatusCode = httpStatus;
    }


}
