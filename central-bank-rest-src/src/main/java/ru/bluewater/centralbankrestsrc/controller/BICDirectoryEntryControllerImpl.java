package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestapi.controller.BICDirectoryEntryController;
import ru.bluewater.centralbankrestsrc.service.BICDirectoryEntryService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bicDirectoryEntry")
@CrossOrigin
public class BICDirectoryEntryControllerImpl  { // implements BICDirectoryEntryController

    private final BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    public BICDirectoryEntryControllerImpl(BICDirectoryEntryService bicDirectoryEntryService) {
        this.bicDirectoryEntryService = bicDirectoryEntryService;
    }

    @GetMapping("/list/{ed807_uuid}")
    public BICDirectoryEntryListResponseDTO findBICDirectoryEntryListByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws RootNotFoundException {
        return bicDirectoryEntryService.findBICDirectoryEntryListByEd807Uuid(ed807Uuid);
    }

    @PostMapping
    public ResponseEntity<BICDirectoryEntryCreateResponseDTO> createBICDirectoryEntry(
            @RequestBody BICDirectoryEntryCreateRequestDTO requestDTO
    )
            throws RootNotFoundException {
        System.out.println("here");
        return ResponseEntity.ok(bicDirectoryEntryService.createBICDirectoryEntry(requestDTO));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<BicDirectoryEntryUpdateResponseDTO> updateBICDirectoryEntry(
            @PathVariable("uuid") UUID uuid,
            @RequestBody BicDirectoryEntryUpdateRequestDTO requestDTO
    )
            throws BicDirectoryEntryNotFoundException, RootNotFoundException {
        return ResponseEntity.ok(bicDirectoryEntryService.updateBICDirectoryEntry(uuid, requestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBICDirectoryEntry(@PathVariable("uuid") UUID uuid)
            throws BicDirectoryEntryNotFoundException {
        bicDirectoryEntryService.deleteBICDirectoryEntry(uuid);
        return ResponseEntity.ok().build();
    }

}
