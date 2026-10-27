package com.skedio.administrationservice.usermanagement.user.repository;

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
@Entity(name = "UsUserApplication")
@Table(name = "user_applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserApplicationJpaEntity {
    @Id
    Long userApplicationId;

    @Column(name = "user_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID userUuid;

    @Column(name = "application_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID applicationUuid;

}
