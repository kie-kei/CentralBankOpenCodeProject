package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"bicDirectoryEntry"})
@Entity(name = "swbics")
public class SWBICSEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull(message = "swbic should be not null")
    @Size(min = 1, max = 11, message = "swbic length should be from 1 to 11")
    private String swbic;

    @NotNull(message = "defaultSWBIC should be not null")
    @Column(name = "default_swbic")
    private Boolean defaultSWBIC;

    @ManyToOne
    @JoinColumn(name = "bic_directory_entry_uuid")
    private BICDirectoryEntryEntity bicDirectoryEntry;
}
