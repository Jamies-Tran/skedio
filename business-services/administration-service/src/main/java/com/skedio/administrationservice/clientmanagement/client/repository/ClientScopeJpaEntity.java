package com.skedio.administrationservice.clientmanagement.client.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ClClientScope")
@Table(name = "client_scopes")
public class ClientScopeJpaEntity {
    @Id
    Long clientScopeId;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "scope_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID scopeUuid;
}
