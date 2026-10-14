package com.skedio.administrationservice.applicationmanagement.applicationuser.repository;

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
@Table(name = "user_applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ApplicationUserJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userApplicationId;

    @Column(name = "user_uuid", nullable = false, updatable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID userUuid;

    @Column(name = "application_uuid", nullable = false, updatable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;
}
