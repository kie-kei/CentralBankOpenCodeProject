package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.RstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.RstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.PartInfoNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RstrListNotFoundException;
import ru.bluewater.centralbankrestsrc.service.RstrListService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RstrListControllerImpl {
    private final RstrListService rstrListService;

    @GetMapping("/rstrList/{rstr_list_uuid}")
    public RstrListGetResponseDTO findRstrListByUuid(
            @PathVariable("rstr_list_uuid") UUID uuid
    ) throws RstrListNotFoundException {
        return rstrListService.findRstrListByUuid(uuid);
    }

    @GetMapping("/participantInfo/{participant_info_uuid}/rstrList")
    public RstrListListResponseDTO findListOfRstrList(
            @PathVariable("participant_info_uuid") UUID uuid
    ) throws ParticipantInfoNotFoundException {
        return rstrListService.findListRstrList(uuid);
    }

    @PostMapping("/participantInfo/{participant_info_uuid}/rstrList")
    public RstrListCreateResponseDTO createRstrList(
            @PathVariable("participant_info_uuid") UUID uuid,
            @RequestBody RstrListCreateRequestDTO requestDTO
    ) throws ParticipantInfoNotFoundException {
        return rstrListService.createRstrList(uuid, requestDTO);
    }

    @PutMapping("/rstrList/{rstr_list_uuid}")
    public RstrListUpdateResponseDTO updateRstrList(
            @PathVariable("rstr_list_uuid") UUID uuid,
            @RequestBody RstrListUpdateRequestDTO requestDTO
    ) throws RstrListNotFoundException {
        return rstrListService.updateRstrList(uuid, requestDTO);
    }

    @DeleteMapping("/rstrList/{uuid}")
    public void deleteRstrList(@PathVariable("uuid") UUID uuid)
            throws RstrListNotFoundException {
        rstrListService.deleteRstrList(uuid);
    }
}
