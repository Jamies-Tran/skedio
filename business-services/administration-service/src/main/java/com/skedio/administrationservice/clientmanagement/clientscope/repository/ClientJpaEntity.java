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
@Entity(name = "CSClient")
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientJpaEntity {
    @Id
    Long clientId;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "enabled")
    Boolean enabled;
}
