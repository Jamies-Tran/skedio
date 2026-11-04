package com.skedio.administrationservice.applicationmanagement.references.client.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "ApApplication")
@Table(name = "applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationGroupJpaEntity {
    @Id
    Long applicationId;

    @Column(name = "application_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;
}
