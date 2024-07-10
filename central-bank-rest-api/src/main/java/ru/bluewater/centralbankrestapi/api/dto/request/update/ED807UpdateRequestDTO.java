package ru.bluewater.centralbankrestapi.api.dto.request.update;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ED807UpdateRequestDTO {
    @Digits(integer = 9, fraction = 0, message = "EDNo should have up to 9 digits")
    @NotNull(message = "EDNo should be not null")
    private Integer edNo; // numberOfElectronicMessage
    @NotNull(message = "EDDate should be not null")
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    @NotNull(message = "EDAuthor should be not null")
    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    private Long edAuthor; // electronicMessageAuthorId unique
    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    private Long edReceiver;
    @NotNull(message = "creationReason sholud be not null")
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "creationReason length should be 4")
    private String creationReason; // creationReasonCode
    @NotNull(message = "creationDateTime should be not null")
    private ZonedDateTime creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ
    @NotNull(message = "infoTypeCode should be not null")
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "infoTypeCode length should be 4")
    private String infoTypeCode; // typeOfInformationPresentationCode
    @NotNull(message = "businessDay should be not null")
    private LocalDate businessDay; // format YYYY-MM-DD
    @Digits(integer = 2, fraction = 0, message = "DirectoryVersion should be in range 0 to 99")
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]
}
