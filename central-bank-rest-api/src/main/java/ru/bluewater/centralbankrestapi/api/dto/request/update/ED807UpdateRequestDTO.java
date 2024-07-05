package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ED807UpdateRequestDTO {
    private Integer edNo; // numberOfElectronicMessage
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD
    private Long edAuthor; // electronicMessageAuthorId unique
    private Long edReceiver;
    private String creationReason; // creationReasonCode
    private ZonedDateTime creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ
    private String infoTypeCode; // typeOfInformationPresentationCode
    private LocalDate businessDay; // format YYYY-MM-DD
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]
}
