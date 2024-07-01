package ru.bluewater.centralbankopencodeproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankopencodeproject.respository.AccountsRepository;
import ru.bluewater.centralbankopencodeproject.respository.ParticipantInfoRepository;
import ru.bluewater.centralbankopencodeproject.service.RootService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/root")
public class RootController {
    private final RootService rootService;
    private final AccountsRepository accountsRepository;
    private final ParticipantInfoRepository participantInfoRepository;

    @Autowired
    public RootController(RootService rootService, AccountsRepository accountsRepository, ParticipantInfoRepository participantInfoRepository){
        this.rootService = rootService;
        this.accountsRepository = accountsRepository;
        this.participantInfoRepository = participantInfoRepository;
    }
    @GetMapping
    public ResponseEntity<?> test() {
//        var test = RootEntity.builder()
//                .EDDate(new Date())
//                .creationReason("123")
//                .xmlns("zxc")
//                .EDNo(12)
//                .infoTypeCode("123")
//                .businessDay(new Date())
//                .directoryVersion(123)
//                .creationDateTime(new Date())
//                .build();
//        rootService.saveRootEntity(test);
        var test = AccountsEntity.builder()
                .account("sdfa")
                .accountCBRBIC("dsa")
                .accountStatus("asd")
                .CK("das")
                .dateOut(LocalDate.now())
                .dateIn(LocalDate.now())
                .regulationAccountType("dsa")
                .build();
        accountsRepository.save(test);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createRoot(@Valid @RequestBody ParticipantInfoEntity rootEntity) {
//        rootService.saveRootEntity(rootEntity);
//        accountsRepository.save(rootEntity);
        participantInfoRepository.save(rootEntity);
        System.out.println(rootEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
