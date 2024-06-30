package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.RstrList;

import java.util.UUID;

public interface RstrListRepository extends JpaRepository<RstrList, UUID> {
}
