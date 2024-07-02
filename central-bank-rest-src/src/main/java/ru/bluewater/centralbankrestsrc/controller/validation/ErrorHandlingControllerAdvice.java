package ru.bluewater.centralbankrestsrc.controller.validation;

import jakarta.validation.ConstraintViolationException;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.CbrException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;
import ru.bluewater.centralbankrestapi.api.exception.UsernameAlreadyExistsException;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ValidationErrorResponse;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ViolationResponseDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        final List<ViolationResponseDTO> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> {
                            String propertyPath = violation.getPropertyPath().toString();
                            String fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
                            return new ViolationResponseDTO(fieldName, violation.getMessage());
                        }
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    //                        violation -> new ViolationResponseDTO(
    //                                violation.getPropertyPath().toString(),
    //                                violation.getMessage()

//    violation -> {
//        String propertyPath = violation.getPropertyPath().toString();
//        String fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
//        return new ViolationResponseDTO(fieldName, violation.getMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ViolationResponseDTO> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ViolationResponseDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO onUsernameAlreadyExistsException(UsernameAlreadyExistsException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO onAuthenticationException(AuthenticationException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(RootNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onFileNotFoundException(RootNotFoundException e){
        return ErrorResponseDTO.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({JAXBException.class, IOException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO onJAXBException(Exception e){
        return ErrorResponseDTO.builder()
                .message("error parsing xml")
                .build();
    }

    @ExceptionHandler(CbrException.class)
    public ResponseEntity<ErrorResponseDTO> onCbrException(CbrException e){
        return ResponseEntity.status(e.getCbrStatusCode())
                .body(
                        ErrorResponseDTO.builder()
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(IncorrectFileTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO onIncorrectFileTypeException(IncorrectFileTypeException e){
        return ErrorResponseDTO.builder()
                .message(e.getMessage())
                .build();
    }

}
