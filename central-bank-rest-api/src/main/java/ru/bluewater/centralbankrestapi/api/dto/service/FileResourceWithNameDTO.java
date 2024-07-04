package ru.bluewater.centralbankrestapi.api.dto.service;

import lombok.*;
import org.springframework.core.io.Resource;

@Getter
@Setter
@Builder
public class FileResourceWithNameDTO {
    private Resource resource;
    private String fileName;
}
