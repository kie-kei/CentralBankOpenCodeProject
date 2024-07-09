package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryFullUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestsrc.service.BICDirectoryEntryService;

import java.util.List;
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

    @GetMapping("/ed807/{ed807_uuid}/bicDirectoryEntry/list")
    public BICDirectoryEntryListResponseDTO findBICDirectoryEntryListByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws ED807NotFoundException {
        return bicDirectoryEntryService.findBICDirectoryEntryListByEd807Uuid(ed807Uuid);
    }

    @PostMapping("/ed807/{ed807_uuid}/bicDirectoryEntry")
    public ResponseEntity<BICDirectoryEntryCreateResponseDTO> createBICDirectoryEntry(
            @PathVariable("ed807_uuid") UUID uuid,
            @RequestBody BICDirectoryEntryCreateRequestDTO requestDTO
    )
            throws ED807NotFoundException {
        return ResponseEntity.ok(bicDirectoryEntryService.createBICDirectoryEntry(uuid, requestDTO));
    }

    @PutMapping("/bicDirectoryEntry/{uuid}")
    public ResponseEntity<BicDirectoryEntryUpdateResponseDTO> updateBICDirectoryEntry(
            @PathVariable("uuid") UUID uuid,
            @RequestBody BicDirectoryEntryUpdateRequestDTO requestDTO
    )
            throws BicDirectoryEntryNotFoundException {
        return ResponseEntity.ok(bicDirectoryEntryService.updateBICDirectoryEntry(uuid, requestDTO));
    }

    @PutMapping("/bicDirectoryEntry")
    public List<BICDirectoryEntryResponseDTO> updateFullBicDirectoryEntry(@RequestBody List<BicDirectoryEntryFullUpdateRequestDTO> requestDTO) {
        return bicDirectoryEntryService.updateFullBicDirectoryEntry(requestDTO);
    }

    @DeleteMapping("/bicDirectoryEntry/{uuid}")
    public ResponseEntity<?> deleteBICDirectoryEntry(@PathVariable("uuid") UUID uuid)
            throws BicDirectoryEntryNotFoundException {
        bicDirectoryEntryService.deleteBICDirectoryEntry(uuid);
        return ResponseEntity.ok().build();
    }

}
