package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;
import ru.bluewater.centralbankrestapi.api.dto.response.RstrListResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParticipantInfoListResponseDTO {
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
    private List<RstrListResponseDTO> rstrList;
}
