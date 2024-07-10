package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.PartInfoNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestsrc.service.PartInfoService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PartInfoController {
    private final PartInfoService partInfoService;

    @GetMapping("/ed807/{ed807_uuid}/partInfo")
    public PartInfoGetResponseDTO findPartInfoByEd807Uuid(
            @PathVariable("ed807_uuid") UUID uuid
    ) throws PartInfoNotFoundException {
        return partInfoService.findPartInfoByEd807Uuid(uuid);
    }

    @PutMapping("/partInfo/{uuid}")
    public PartInfoUpdateResponseDTO updatePartInfoByEd807Uuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody PartInfoUpdateRequestDTO requestDTO
    ) throws PartInfoNotFoundException {
        return partInfoService.updatePartInfoByEd807Uuid(uuid, requestDTO);
    }

    @PostMapping("/ed807/{ed807_uuid}/partInfo")
    public PartInfoCreateResponseDTO createPartInfoByEd807Uuid(
            @PathVariable("ed807_uuid") UUID uuid,
            @RequestBody PartInfoCreateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        return partInfoService.createPartInfoByEd807Uuid(uuid, requestDTO);
    }

    @DeleteMapping("/partInfo/{uuid}")
    public void deletePartInfo(@PathVariable("uuid") UUID uuid)
            throws PartInfoNotFoundException {
        partInfoService.deletePartInfo(uuid);
    }
}
