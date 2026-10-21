package com.skedio.administrationservice.usermanagement.userapplication.repository;

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
@Entity(name = "UaApplicationGroup")
@Table(name = "applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
class ApplicationGroupJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;

    @Column(name = "application_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;

    @Column(name = "enabled")
    Boolean enabled;
}
