package ru.bluewater.centralbankopencodeproject.service;

import jakarta.xml.bind.JAXBException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.bluewater.centralbankopencodeproject.entity.Accounts;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntry;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfo;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.util.XmlParser;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    private final RootService rootService;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryService bicDirectoryEntryService;
    private final AccountsService accountsService;
    private final RstrListService rstrListService;
    
    private Logger logger = LoggerFactory.getLogger("fileService logger");

    @Autowired
    public FileService(RootService rootService, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService, AccountsService accountsService, RstrListService rstrListService) {
        this.rootService = rootService;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.accountsService = accountsService;
        this.rstrListService = rstrListService;
    }

    @Transactional
    public void createRootFromFile(MultipartFile multipartFile) throws JAXBException, IOException {
        RootEntity rootEntity = XmlParser.fromXmlFile(multipartFile.getInputStream());
        
        logger.info("rootEntity saved");

        for (BICDirectoryEntry entry : rootEntity.getBicDirectoryEntry()) {
            entry.setRootEntity(rootEntity);
            ParticipantInfo participantInfo = entry.getParticipantInfo();
            List<Accounts> accounts = entry.getAccounts();
            if (participantInfo != null && participantInfo.getUuid() == null) {
                participantInfo.setBicDirectoryEntry(entry);
                participantInfoService.createParticipantInfo(participantInfo);
            }
            if(accounts != null){
                for(Accounts acc: accounts){
                    acc.setBicDirectoryEntry(entry);
                }
            }
            bicDirectoryEntryService.createBICDirectoryEntry(entry);
        }
        
        rootService.saveRootEntity(rootEntity);
    }

    public RootEntity getFileContentByUuid(UUID uuid){
        RootEntity rootEntity = rootService.findRootByUuid(uuid);
        logger.info(rootEntity.getBicDirectoryEntry().toString());
        return rootEntity;
    }
    @SneakyThrows
    public Resource getFileByUuid(UUID uuid) {
        var rootEntity = rootService.findRootByUuid(uuid);
        var content = XmlParser.toXml(rootEntity);
        var resource = new ByteArrayResource(content.getBytes());
        return resource;
    }
}
