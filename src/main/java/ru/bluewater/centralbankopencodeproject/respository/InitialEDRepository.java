package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.InitialEDEntity;

import java.util.UUID;

public interface InitialEDRepository extends JpaRepository<InitialEDEntity, UUID> {
}
