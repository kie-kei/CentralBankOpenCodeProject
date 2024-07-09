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
import ru.bluewater.centralbankrestapi.api.dto.response.error.ValidationErrorResponse;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ViolationResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.*;

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

    @ExceptionHandler(ED807NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onFileNotFoundException(ED807NotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(PartInfoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onPartInfoNotFoundException(PartInfoNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(AccountsNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onAccountsNotFoundException(AccountsNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(ParticipantInfoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onParticipantInfoNotFoundException(ParticipantInfoNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(BicDirectoryEntryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onBicDirectoryEntryNotFoundException(BicDirectoryEntryNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(RstrListNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onRstrListNotFoundException(RstrListNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(AccRstrListNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onAccRstrListNotFoundException(AccRstrListNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(InitialEDNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO onInitialEdNotFoundException(InitialEDNotFoundException e){
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler({JAXBException.class, IOException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO onJAXBException(Exception e){
        return new ErrorResponseDTO("error parsing xml");
    }

    @ExceptionHandler(CbrException.class)
    public ResponseEntity<ErrorResponseDTO> onCbrException(CbrException e){
        return ResponseEntity.status(e.getCbrStatusCode())
                .body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(IncorrectFileTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO onIncorrectFileTypeException(IncorrectFileTypeException e){
        return new ErrorResponseDTO(e.getMessage());
    }

}
