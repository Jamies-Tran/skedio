package com.skedio.administrationservice.applicationmanagement.applications.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Setter
@Entity(name = "ApApplicationUser")
@Table(name = "user_applications")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationUserJpaEntity {
    @Id
    Long userApplicationId;

    @Column(name = "user_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID userUuid;

    @Column(name = "application_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;
}
