package com.skedio.administrationservice.usermanagement.user.repository;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserEntity extends BaseAuditorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "user_uuid", updatable = false, nullable = false)
    UUID userUuid;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "phone", unique = true)
    String phone;

    @Column(name = "status_code", nullable = false)
    String statusCode;

    @Column(name = "status_name", nullable = false)
    String statusName;

    @Column(name = "search", nullable = false)
    String search;
}
