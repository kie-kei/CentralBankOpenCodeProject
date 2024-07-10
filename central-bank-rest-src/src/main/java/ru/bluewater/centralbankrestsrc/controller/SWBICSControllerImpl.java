package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.SWBICSListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.SWBICSNotFoundException;
import ru.bluewater.centralbankrestsrc.service.SWBICSService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SWBICSControllerImpl {
    private final SWBICSService swbicsService;

    @GetMapping("/swbics/{swbics_uuid}")
    public SWBICSGetResponseDTO findSwbicsByUuid(
            @PathVariable("swbics_uuid") UUID uuid
    ) throws SWBICSNotFoundException {
        return swbicsService.findSwbicsByUuid(uuid);
    }

    @PutMapping("/swbics/{swbics_uuid}")
    public SWBICSUpdateResponseDTO updateSwbicsByUuid(
            @PathVariable("swbics_uuid") UUID uuid,
            @RequestBody SWBICSUpdateRequestDTO requestDTO
    ) throws SWBICSNotFoundException {
        return swbicsService.updateSwbics(uuid, requestDTO);
    }

    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/swbics")
    public SWBICSListResponseDTO findListSwbics(
            @PathVariable("bic_directory_entry_uuid") UUID uuid) throws BicDirectoryEntryNotFoundException {
        return swbicsService.findListSWBICS(uuid);
    }

    @PostMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/swbics")
    public SWBICSCreateResponseDTO createSwbics(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody SWBICSCreateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException {
        return swbicsService.createSwbics(uuid, requestDTO);
    }

    @DeleteMapping("/swbics/{uuid}")
    public void deleteSWBICS(@PathVariable("uuid") UUID uuid)
            throws SWBICSNotFoundException {
        swbicsService.deleteSWBICS(uuid);
    }
}
