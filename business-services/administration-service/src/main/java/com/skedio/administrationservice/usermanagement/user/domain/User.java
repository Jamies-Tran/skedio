package com.skedio.administrationservice.usermanagement.user.domain;

import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.With;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(
    Long userId,
    UUID userUuid,
    @With
    String username,
    @With
    String password,
    @With
    String firstName,
    @With
    String lastName,
    @With
    String email,
    @With
    String phone,
    @With
    String statusCode,
    @With
    String statusName,
    @With
    String search,
    String createdBy,
    String updatedBy,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public User encodePasswordWith(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);
        return this.withPassword(encodedPassword);
    }

    public User computeBeforeSave() {
        String search = "%s%s%s%s%s".formatted(
                StringConvertUtils.normalizeWhiteSpace(username),
                StringConvertUtils.normalizeWhiteSpace(firstName),
                StringConvertUtils.normalizeWhiteSpace(lastName),
                StringConvertUtils.normalizeWhiteSpace(email),
                StringConvertUtils.normalizeWhiteSpace(phone)
        );
        String statusCode = StringUtils.hasText(this.statusCode) ? this.statusCode : EnumUserStatus.ACTIVE.getCode();
        String statusName = StringUtils.hasText(this.statusName) ? this.statusName : EnumUserStatus.ACTIVE.getName();
        return this
                .withSearch(search)
                .withStatusCode(statusCode)
                .withStatusName(statusName);
    }

    public User active() {
        EnumUserStatus active = EnumUserStatus.ACTIVE;
        return this
                .withStatusCode(active.getCode())
                .withStatusName(active.getName());
    }

    public User inactive() {
        EnumUserStatus inactive = EnumUserStatus.INACTIVE;
        return this
                .withStatusCode(inactive.getCode())
                .withStatusName(inactive.getName());
    }

    public User apply(User user) {
        return this
                .withUsername(user.username)
                .withFirstName(user.firstName)
                .withLastName(user.lastName)
                .withEmail(user.email)
                .withPhone(user.phone);
    }
}
