package ru.bluewater.centralbankopencodeproject.respository;

import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;

import java.util.UUID;

@Validated
public interface RootEntityRepository extends JpaRepository<RootEntity, UUID> {

}
