package com.skedio.administrationservice.clientmanagement.client.repository;

import com.skedio.corestarter.auditor.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ClientJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientId;

    @Column(name = "application_uuid", nullable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;

    @Column(name = "client_uuid", unique = true, updatable = false)
    @JdbcTypeCode(Types.CHAR)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    UUID clientUuid;

    @Column(name = "client_name", nullable = false)
    String clientName;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "enabled", nullable = false)
    Boolean enabled;

    @Column(name = "description")
    String description;

    @Column(name = "search", nullable = false)
    String search;
}
