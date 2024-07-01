package ru.bluewater.centralbankopencodeproject.api.dto.response;


import lombok.*;
import ru.bluewater.centralbankopencodeproject.entity.RstrListEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantInfoResponseDTO {
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