package com.skedio.administrationservice.scopemanagement.scope.repository;

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
@Table(name = "scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ScopeJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long scopeId;

    @Column(name = "scope_uuid", nullable = false, unique = true, updatable = false)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(Types.CHAR)
    UUID scopeUuid;

    @Column(name = "scope_name", unique = true, nullable = false)
    String scopeName;

    @Column(name = "description")
    String description;

    @Column(name = "enabled", nullable = false)
    Boolean enabled;

    @Column(name = "search", nullable = false)
    String search;
}
