package com.skedio.administrationservice.applicationmanagement.applications.repository;

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
@Entity
@Table(name = "applications")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class ApplicationGroupJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;

    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "application_uuid", unique = true, updatable = false, nullable = false)
    UUID applicationUuid;

    @Column(name = "application_name", nullable = false)
    String applicationName;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "description")
    String description;

    @Column(name = "enabled")
    Boolean enabled;

    @Column(name = "search", nullable = false)
    String search;
}
