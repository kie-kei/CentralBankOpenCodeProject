package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface AccountsRepository extends JpaRepository<AccountsEntity, UUID> {
    @Query("SELECT b FROM accounts b JOIN b.accRstrList a WHERE a = :accRstrList")
    AccountsEntity findByAccRstrList(AccRstrListEntity accRstrList);
}
