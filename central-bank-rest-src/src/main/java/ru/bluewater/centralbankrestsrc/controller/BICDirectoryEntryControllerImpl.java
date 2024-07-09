package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.full.BICDirectoryEntryFullResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestsrc.service.BICDirectoryEntryService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class BICDirectoryEntryControllerImpl  { // implements BICDirectoryEntryController

    private final BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    public BICDirectoryEntryControllerImpl(BICDirectoryEntryService bicDirectoryEntryService) {
        this.bicDirectoryEntryService = bicDirectoryEntryService;
    }

    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    public BICDirectoryEntryGetResponseDTO findBicDirectoryEntryByUuid(
            @PathVariable("bic_directory_entry_uuid") UUID uuid
    ) throws BicDirectoryEntryNotFoundException {
        return bicDirectoryEntryService.findBicDirectoryEntryByUuid(uuid);
    }

    @GetMapping("/ed807/{ed807_uuid}/bicDirectoryEntry/full")
    public BICDirectoryEntryFullResponseDTO findFullBicDirectoryEntryByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws ED807NotFoundException {
        return bicDirectoryEntryService.findBicDirectoryEntryFullByEd807Uuid(ed807Uuid);
    }

    @GetMapping("/ed807/{ed807_uuid}/bicDirectoryEntry")
    public BICDirectoryEntryListResponseDTO findBicDirectoryEntryListByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws ED807NotFoundException {
        return bicDirectoryEntryService.findBicDirectoryEntryListByEd807Uuid(ed807Uuid);
    }

    @PostMapping("/ed807/{ed807_uuid}/bicDirectoryEntry")
    public BICDirectoryEntryCreateResponseDTO createBicDirectoryEntry(
            @PathVariable("ed807_uuid") UUID uuid,
            @RequestBody BICDirectoryEntryCreateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        return bicDirectoryEntryService.createBICDirectoryEntry(uuid, requestDTO);
    }

    @PutMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    public BicDirectoryEntryUpdateResponseDTO updateBicDirectoryEntry(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody BicDirectoryEntryUpdateRequestDTO requestDTO
    )
            throws BicDirectoryEntryNotFoundException {
        return bicDirectoryEntryService.updateBicDirectoryEntry(uuid, requestDTO);
    }

    @DeleteMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    public void deleteBicDirectoryEntry(@PathVariable("bic_directory_entry_uuid") UUID uuid)
            throws BicDirectoryEntryNotFoundException {
        bicDirectoryEntryService.deleteBicDirectoryEntry(uuid);
    }

}
