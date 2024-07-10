package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.InitialEDNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestsrc.service.ParticipantInfoService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParticipantInfoControllerImpl {
    private final ParticipantInfoService participantInfoService;

    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/participantInfo")
    public ParticipantInfoGetResponseDTO findParticipantInfoByUuid(@PathVariable("bic_directory_entry_uuid") UUID uuid)
            throws ParticipantInfoNotFoundException {
        return participantInfoService.findParticipantInfoByUuid(uuid);
    }

    @PutMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/participantInfo")
    public ParticipantInfoUpdateResponseDTO updateParticipantInfoByUuid(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody ParticipantInfoUpdateRequestDTO requestDTO
            ) throws ParticipantInfoNotFoundException{
        return participantInfoService.updateParticipantInfo(uuid, requestDTO);
    }
}
