package ru.bluewater.centralbankopencodeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.respository.RootEntityRepository;
import ru.bluewater.centralbankopencodeproject.service.RootService;

import java.util.Date;

@RestController
public class HealthController {
    private RootService rootService;

    @Autowired
    public HealthController(RootService rootService) {
        this.rootService = rootService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        var test = RootEntity.builder()
                .directoryVersion(1)
                .businessDay(new Date())
                .xmlns("sad")
                .infoTypeCode("dsa")
                .creationReason("123")
                .EDDate(new Date())
                .EDNo(123)
                .EDAuthor(123L)
                .creationDateTime(new Date())
                .creationReason("das")
                .build();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
