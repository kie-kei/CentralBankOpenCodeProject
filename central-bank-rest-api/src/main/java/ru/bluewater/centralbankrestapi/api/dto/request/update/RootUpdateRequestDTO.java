package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.InitialEDResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.PartInfoResponseDTO;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootUpdateRequestDTO {
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
