package ru.bluewater.centralbankopencodeproject.api.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BICDirectoryEntryResponseDTO {
    private UUID uuid;
    private String BIC;
    private String changeType;
    private ParticipantInfoResponseDTO participantInfo;
    private List<AccountsResponseDTO> accounts;
    private List<SWBICSResponseDTO> swbics;

}
