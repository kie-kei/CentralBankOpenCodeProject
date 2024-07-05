package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BicDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BicDirectoryEntryCreateResponseDTO;
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
public class BICDirectoryEntryControllerImpl implements BICDirectoryEntryController {

    private final BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    public BICDirectoryEntryControllerImpl(BICDirectoryEntryService bicDirectoryEntryService) {
        this.bicDirectoryEntryService = bicDirectoryEntryService;
    }

    @PostMapping
    public ResponseEntity<BicDirectoryEntryCreateResponseDTO> createBICDirectoryEntry(
            @RequestBody BicDirectoryEntryCreateRequestDTO requestDTO,
            Principal principal
    )
            throws RootNotFoundException {
        return ResponseEntity.ok(bicDirectoryEntryService.createBICDirectoryEntryFromDTO(requestDTO, principal));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<BicDirectoryEntryUpdateResponseDTO> updateBICDirectoryEntry(
            @PathVariable("uuid") UUID uuid,
            @RequestBody BicDirectoryEntryUpdateRequestDTO requestDTO,
            Principal principal
    )
            throws BicDirectoryEntryNotFoundException, RootNotFoundException {
        return ResponseEntity.ok(bicDirectoryEntryService.updateBicDirectoryEntry(uuid, requestDTO, principal));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteBICDirectoryEntry(@PathVariable("uuid") UUID uuid)
            throws BicDirectoryEntryNotFoundException {
        bicDirectoryEntryService.deleteBICDirectoryEntry(uuid);
        return ResponseEntity.ok().build();
    }

}
