package com.skedio.administrationservice.applicationmanagement.applications.repository;

import com.skedio.corestarter.auditor.BaseAuditorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "ApClient")
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientJpaEntity extends BaseAuditorEntity {
    @Id
    Long clientId;

    @Column(name = "application_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "client_name")
    String clientName;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "enabled")
    Boolean enabled;

    @Column(name = "description")
    String description;

    @Column(name = "search")
    String search;
}
