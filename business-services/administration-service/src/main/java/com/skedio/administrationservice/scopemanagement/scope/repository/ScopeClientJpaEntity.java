package com.skedio.administrationservice.scopemanagement.scope.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ScClientScope")
@Table(name = "client_scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScopeClientJpaEntity {
    @Id
    Long clientScopeId;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "scope_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID scopeUuid;
}
