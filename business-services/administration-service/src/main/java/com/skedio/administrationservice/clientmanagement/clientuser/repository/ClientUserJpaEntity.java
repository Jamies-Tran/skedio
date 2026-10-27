package com.skedio.administrationservice.clientmanagement.clientuser.repository;

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
@Table(name = "client_users")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ClientUserJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clientUserId;

    @Column(name = "client_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID clientUuid;

    @Column(name = "user_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID userUuid;
}
