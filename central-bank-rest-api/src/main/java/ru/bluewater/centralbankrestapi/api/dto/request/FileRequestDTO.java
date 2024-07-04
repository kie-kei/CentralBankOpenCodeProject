package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileRequestDTO {
    private MultipartFile file;
}
