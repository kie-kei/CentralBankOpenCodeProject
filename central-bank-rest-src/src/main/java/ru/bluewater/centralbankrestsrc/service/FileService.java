package ru.bluewater.centralbankrestsrc.service;

import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.bluewater.centralbankrestapi.api.dto.request.FileRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.service.FileResourceWithNameDTO;
import ru.bluewater.centralbankrestapi.api.exception.CbrException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.dto.FileDTO;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.entity.xml.ED807;
import ru.bluewater.centralbankrestsrc.mapper.entity.ED807EntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.xml.ED807Mapper;
import ru.bluewater.centralbankrestsrc.util.FileUtil;
import ru.bluewater.centralbankrestsrc.util.XmlParser;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    private final ED807Service ed807Service;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryService bicDirectoryEntryService;
    private final FileUtil fileUtil;
    private final ED807Mapper ed807Mapper;
    private final ED807EntityMapper ed807EntityMapper;

    @Value("${central-bank-uri}")
    private String centralBankURI;
    //private final URI centralBankURI = URI.create("https://cbr.ru/s/newbik");
    private final Logger logger = LoggerFactory.getLogger("fileService logger");


    @Autowired
    public FileService(ED807Service ed807Service, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService, ED807Mapper ed807Mapper, ED807EntityMapper ed807EntityMapper, FileUtil fileUtil) {
        this.ed807Service = ed807Service;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.ed807Mapper = ed807Mapper;
        this.ed807EntityMapper = ed807EntityMapper;
        this.fileUtil = fileUtil;
    }

    @Transactional
    public FileUploadResponseDTO createRootFromFile(FileRequestDTO requestDTO, Principal principal) throws
            JAXBException, IOException, IncorrectFileTypeException {
        FileDTO dto = parseMultipartToFileDTO(requestDTO.getFile());
        ED807Entity ed807Entity = ed807Service.createED807FromFileDTO(dto, principal);

        return FileUploadResponseDTO.builder()
                .uuid(ed807Entity.getUuid())
                .build();
    }

    private FileDTO parseMultipartToFileDTO(MultipartFile multipartFile) throws IncorrectFileTypeException, IOException, JAXBException {
        String originalFileName = multipartFile.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));

        if (!fileType.equals(".xml"))
            throw new IncorrectFileTypeException(fileType);

        ED807 ed807 = XmlParser.fromXmlFile(multipartFile.getInputStream());

        return new FileDTO(originalFileName, ed807);
    }


    public FileResourceWithNameDTO getFileByRootUuid(UUID uuid) throws RootNotFoundException, JAXBException {
        var rootEntity = ed807Service.findRootByUuid(uuid);
        var content = XmlParser.toXml(ed807Mapper.dtoToED807(rootEntity));
        var resource = new ByteArrayResource(content.getBytes());
        return FileResourceWithNameDTO.builder()
                .resource(resource)
                .fileName(rootEntity.getFileName())
                .build();
    }


    @Transactional
    public FileUploadResponseDTO createRootFromCBR(Principal principal) throws CbrException, IOException, JAXBException, IncorrectFileTypeException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<byte[]> response = restTemplate.exchange(
                URI.create(centralBankURI), HttpMethod.GET, null, byte[].class);

        if (response.getBody() == null || response.getBody().length == 0)
            throw new CbrException(response.getStatusCode());

        MultipartFile xmlFile = fileUtil.extractXMLMultipartFileFromZIPByteArray(response.getBody());

        FileDTO fileDTO = parseMultipartToFileDTO(xmlFile);
        ED807Entity ed807Entity = ed807Service.createED807FromFileDTO(fileDTO, principal);

        return FileUploadResponseDTO.builder()
                .uuid(ed807Entity.getUuid())
                .build();
    }

}
