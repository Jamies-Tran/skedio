package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import com.skedio.corestarter.auditor.BaseAuditorEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ClientScopeJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientScopeId;

    @Column(name = "client_uuid", nullable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "scope_uuid", nullable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID scopeUuid;

    @Column(name = "resource_path", nullable = false)
    String resourcePath;
}
