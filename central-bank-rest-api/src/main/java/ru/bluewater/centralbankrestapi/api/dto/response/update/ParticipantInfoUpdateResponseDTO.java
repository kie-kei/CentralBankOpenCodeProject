package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ParticipantInfoUpdateResponseDTO {
    private UUID uuid;
    private String nameP;
    private String englName;
    private String regN;
    private String cntrCd;
    private String rgn;
    private String ind;
    private String tnp;
    private String nnp;
    private String adr;
    private String prntBIC;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String ptType;
    private String srvcs;
    private String xchType;
    private String uid;
    private String participantStatus;
}
