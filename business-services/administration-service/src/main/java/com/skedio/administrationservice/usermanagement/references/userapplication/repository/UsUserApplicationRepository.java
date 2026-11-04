package com.skedio.administrationservice.usermanagement.references.userapplication.repository;

import com.skedio.administrationservice.usermanagement.references.userapplication.domain.UsUserApplication;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsUserApplicationRepository {
    UsUserApplicationJpaRepository repository;
    UsUserApplicationEntityMapper mapper;

    public void save(UsUserApplication userApplication) {
        UsUserApplicationJpaEntity entity = mapper.toEntity(userApplication);
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
