package com.skedio.administrationservice.clientmanagement.clientuser.repository;

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
@Entity(name = "CUClient")
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientId;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "enabled")
    Boolean enabled;
}
