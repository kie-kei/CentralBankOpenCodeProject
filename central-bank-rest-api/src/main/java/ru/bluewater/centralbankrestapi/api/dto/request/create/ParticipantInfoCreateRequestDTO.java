package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.request.RstrListRequestDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ParticipantInfoCreateRequestDTO {
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
