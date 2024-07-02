package ru.bluewater.centralbankrestapi.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootResponseDTO {
    private UUID uuid;
    private String fileName;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate updatedAt;
    private String updatedBy;
    private List<BICDirectoryEntryResponseDTO> bicDirectoryEntry;
    private PartInfoResponseDTO partInfo;
    private InitialEDResponseDTO initialED;
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
