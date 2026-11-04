package com.skedio.administrationservice.applicationmanagement.references.applicationuser.repository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ApApplicationUserDao {
    Long getUserApplicationId();

    UUID getUserUuid();

    String getUsername();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhone();

    String getCreatedBy();

    LocalDateTime getCreatedAt();

    String getUpdatedBy();

    LocalDateTime getUpdatedAt();
}
