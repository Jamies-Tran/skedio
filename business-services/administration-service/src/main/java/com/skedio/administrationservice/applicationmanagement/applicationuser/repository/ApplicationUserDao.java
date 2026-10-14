package com.skedio.administrationservice.applicationmanagement.applicationuser.repository;

import java.util.UUID;

public interface ApplicationUserDao {
    Long getUserApplicationId();

    UUID getUserUuid();

    String getUsername();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhone();
}
