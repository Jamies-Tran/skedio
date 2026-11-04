package com.skedio.administrationservice.applicationmanagement.references.client.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApClientRepository {
    ApClientJpaRepository repository;

    public void deleteAllByApplicationUuid(UUID applicationUuid) {
        repository.deleteAllByApplicationUuid(applicationUuid);
    }

    public Boolean existApplicationByApplicationUuid(UUID applicationUuid) {
        return repository.existApplicationByApplicationUuid(applicationUuid);
    }
}
