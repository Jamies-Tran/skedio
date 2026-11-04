package com.skedio.administrationservice.permissionmanagement.permission.repository;

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
@Table(name = "permissions")
@FieldDefaults(level = AccessLevel.PRIVATE)
class PermissionJpaEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long permissionId;

    @Column(name = "permission_uuid", unique = true, nullable = false, updatable = false)
    @JdbcTypeCode(Types.CHAR)
    UUID permissionUuid;

    @Column(name = "permission_name", nullable = false)
    String permissionName;

    @Column(name = "description")
    String description;

    @Column(name = "enabled")
    Boolean enabled;

    @Column(name = "search")
    String search;
}
