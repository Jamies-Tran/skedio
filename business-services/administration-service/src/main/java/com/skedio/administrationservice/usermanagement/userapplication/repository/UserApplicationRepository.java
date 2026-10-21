package com.skedio.administrationservice.usermanagement.userapplication.repository;

import com.skedio.administrationservice.usermanagement.userapplication.domain.UserApplication;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApplicationRepository {
    UserApplicationJpaRepository repository;
    UserApplicationEntityMapper mapper;

    public void save(UserApplication userApplication) {
        UserApplicationJpaEntity entity = mapper.toEntity(userApplication);
        repository.save(entity);
    }

    public void deleteByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid) {
        repository.deleteByUserUuidAndApplicationUuid(userUuid, applicationUuid);
    }

    public Boolean existsByUserUuidAndApplicationUuid(UUID userUuid, UUID applicationUuid) {
        return repository.existsByUserUuidAndApplicationUuid(userUuid, applicationUuid);
    }

    public Boolean existsApplicationByApplicationUuidAndEnabled(UUID applicationUuid) {
        return repository.existsApplicationByApplicationUuidAndEnabled(applicationUuid);
    }

    public Boolean existsUserByUserUuid(UUID userUuid) {
        return repository.existsUserByUserUuid(userUuid);
    }
}
