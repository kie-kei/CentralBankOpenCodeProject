package ru.bluewater.centralbankopencodeproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;

import java.util.UUID;

public interface AccountsRepository extends JpaRepository<AccountsEntity, UUID> {
}
