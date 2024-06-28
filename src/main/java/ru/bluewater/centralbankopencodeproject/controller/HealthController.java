package ru.bluewater.centralbankopencodeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.respository.RootEntityRepository;

import java.util.Date;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        var test = RootEntity.builder()
                .creationReason("123")
                .build();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
