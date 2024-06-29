package ru.bluewater.centralbankopencodeproject.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.respository.RootEntityRepository;

@Service
@Validated
public class RootService {
    private final RootEntityRepository repository;

    @Autowired
    public RootService(RootEntityRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public RootEntity saveRootEntity(@Valid RootEntity rootEntity){
        return repository.save(rootEntity);
    }
}
