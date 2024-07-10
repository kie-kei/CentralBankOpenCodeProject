package ru.bluewater.centralbankrestsrc.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

import java.util.UUID;
@RepositoryRestResource(exported = false)
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntryEntity, UUID> {
    @Query("SELECT b FROM bic_directory_entry b JOIN b.accounts a WHERE a = :account")
    BICDirectoryEntryEntity findByAccount(AccountsEntity account);

    @Query("SELECT b FROM bic_directory_entry b JOIN b.swbics a WHERE a = :swbics")
    BICDirectoryEntryEntity findBySwbics(SWBICSEntity swbics);
}
