package ru.bluewater.centralbankrestapi.api.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResourceWithNameDTO {
    private Resource resource;
    private String fileName;
}
