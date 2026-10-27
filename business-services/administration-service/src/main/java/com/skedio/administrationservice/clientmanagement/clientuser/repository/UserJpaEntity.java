package com.skedio.administrationservice.clientmanagement.clientuser.repository;

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
@Entity(name = "CUUser")
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJpaEntity {
    @Id
    Long userId;

    @Column(name = "user_uuid")
    @JdbcTypeCode(Types.CHAR)
    UUID userUuid;

    @Column(name = "username")
    String username;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "search")
    String search;
}
