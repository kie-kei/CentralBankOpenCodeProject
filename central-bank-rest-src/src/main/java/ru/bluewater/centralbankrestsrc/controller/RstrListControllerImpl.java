package ru.bluewater.centralbankrestsrc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/participantInfo/{participant_info_uuid}/")
@RequiredArgsConstructor
public class RstrListControllerImpl {
}
