package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.InitialEDCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.InitialEDUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.InitialEDCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.InitialEDGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.InitialEDUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccRstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.InitialEDNotFoundException;
import ru.bluewater.centralbankrestsrc.service.InitialEDService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InitialEDController {
    private final InitialEDService initialEDService;

    @GetMapping("/ed807/{ed807_uuid}/initialEd")
    public InitialEDGetResponseDTO findInitialEdByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid
    ) throws InitialEDNotFoundException {
        return initialEDService.findInitialEdByEd807Uuid(ed807Uuid);
    }

    @PutMapping("/initialEd/{uuid}")
    public InitialEDUpdateResponseDTO updateInitialEd(
            @PathVariable("uuid") UUID uuid,
            @RequestBody InitialEDUpdateRequestDTO requestDTO
    ) throws InitialEDNotFoundException {
        return initialEDService.updateInitialEdByEd807Uuid(uuid, requestDTO);
    }

    @PostMapping("/ed807/{ed807_uuid}/initialEd")
    public InitialEDCreateResponseDTO createInitialEDByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid,
            @RequestBody InitialEDCreateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        return initialEDService.createInitialEdByEd807Uuid(ed807Uuid, requestDTO);
    }

    @DeleteMapping("/initialEd/{uuid}")
    public void deleteInitialED(@PathVariable("uuid") UUID uuid)
            throws InitialEDNotFoundException {
        initialEDService.deleteInitialED(uuid);
    }
}
