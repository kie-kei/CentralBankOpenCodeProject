package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.service.PartInfoService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/partInfo")
@RequiredArgsConstructor
public class PartInfoController {
    private final PartInfoService partInfoService;

    @GetMapping("/{ed807_uuid}")
    public PartInfoGetResponseDTO findPartInfoByEd807Uuid(
            @PathVariable("ed807_uuid") UUID uuid
    ) throws RootNotFoundException {
        return partInfoService.findPartInfoByEd807Uuid(uuid);
    }

    @PutMapping("")
    public PartInfoUpdateResponseDTO updatePartInfoByEd807Uuid(
            @RequestBody PartInfoUpdateRequestDTO requestDTO
    ) throws RootNotFoundException {
        return partInfoService.updatePartInfoByEd807Uuid(requestDTO);
    }
}
