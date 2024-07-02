package ru.bluewater.centralbankopencodeproject.service;

import jakarta.xml.bind.JAXBException;
import lombok.SneakyThrows;
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
import ru.bluewater.centralbankopencodeproject.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.service.FileResourceWithNameDTO;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.ED807;
import ru.bluewater.centralbankopencodeproject.mapper.entity.RootEntityMapper;
import ru.bluewater.centralbankopencodeproject.mapper.xml.ED807Mapper;
import ru.bluewater.centralbankopencodeproject.respository.RootRepository;
import ru.bluewater.centralbankopencodeproject.util.FileUtil;
import ru.bluewater.centralbankopencodeproject.util.XmlParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    private final RootService rootService;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryService bicDirectoryEntryService;
    private final FileUtil fileUtil;
    private final ED807Mapper ed807Mapper;
    private final RootEntityMapper rootEntityMapper;
    private final URI centralBankURI = URI.create("https://cbr.ru/s/newbik");

    @Value("${destination.dir}")
    private String destinationToZip;
    private Logger logger = LoggerFactory.getLogger("fileService logger");


    @Autowired
    public FileService(RootService rootService, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService, ED807Mapper ed807Mapper, RootEntityMapper rootEntityMapper, FileUtil fileUtil) {
        this.rootService = rootService;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.ed807Mapper = ed807Mapper;
        this.rootEntityMapper = rootEntityMapper;
        this.fileUtil = fileUtil;
    }

    @Transactional
    public FileUploadResponseDTO createRootFromFile(MultipartFile multipartFile) throws JAXBException, IOException {
        ED807 ed807 = XmlParser.fromXmlFile(multipartFile.getInputStream());
        RootEntity rootEntity = ed807Mapper.toRootEntity(ed807);
        rootEntity.setFileName(multipartFile.getOriginalFilename());
        logger.info("rootEntity saved");

        rootService.saveRootEntity(rootEntity);

        return new FileUploadResponseDTO(rootEntity.getUuid());
    }

    public RootResponseDTO getFileContentByUuid(UUID uuid){
        RootEntity rootEntity = rootService.findRootByUuid(uuid);
        logger.info(rootEntity.getBicDirectoryEntry().toString());
        return rootEntityMapper.toRootResponseDTO(rootEntity);
    }
    @SneakyThrows
    public FileResourceWithNameDTO getFileByUuid(UUID uuid) {
        var rootEntity = rootService.findRootByUuid(uuid);
        var content = XmlParser.toXml(ed807Mapper.toED807(rootEntity));
        var resource = new ByteArrayResource(content.getBytes());
        return new FileResourceWithNameDTO(resource, rootEntity.getFileName());
    }

    @SneakyThrows
    @Transactional
    public FileUploadResponseDTO createRootFromCBR() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<byte[]> response = restTemplate.exchange(
                centralBankURI, HttpMethod.GET, null, byte[].class);

        MultipartFile xmlFile = fileUtil.extractXMLMultipartFileFromZIPByteArray(response.getBody());

        return createRootFromFile(xmlFile);
    }
}
