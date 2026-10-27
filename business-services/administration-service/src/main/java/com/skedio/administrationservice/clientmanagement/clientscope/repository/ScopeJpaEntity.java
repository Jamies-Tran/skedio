package com.skedio.administrationservice.clientmanagement.clientscope.repository;

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
@Entity(name = "ClScope")
@Table(name = "scopes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScopeJpaEntity {
    @Id
    Long scopeId;

    @Column(name = "scope_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID scopeUuid;

    @Column(name = "scope_name")
    String scopeName;

    @Column(name = "search")
    String search;
}
