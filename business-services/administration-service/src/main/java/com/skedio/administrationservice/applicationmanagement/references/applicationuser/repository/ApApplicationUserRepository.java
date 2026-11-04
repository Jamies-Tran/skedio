package com.skedio.administrationservice.applicationmanagement.references.applicationuser.repository;

import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.ApApplicationUser;
import com.skedio.administrationservice.applicationmanagement.references.applicationuser.domain.argument.ApplicationGroupGetUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApApplicationUserRepository {
    ApApplicationUserJpaRepository repository;
    ApApplicationUserDaoMapper daoMapper;
    ApApplicationUserEntityMapper entityMapper;

    public void saveAll(List<ApApplicationUser> applicationUsers) {
        List<ApApplicationUserJpaEntity> entities = entityMapper.toEntity(applicationUsers);
        repository.saveAll(entities);
    }

    public Page<ApApplicationUser> findUserInApplication(ApplicationGroupGetUser search, PageRequest pageRequest) {
        return repository.findUserDaoInApplication(search, pageRequest)
                .map(daoMapper::toDomain);
    }

    public void clearUserInApplication(UUID applicationUuid) {
        repository.deleteAllByApplicationUuid(applicationUuid);
    }

    public Boolean existsAllUsersByUserUuidList(List<UUID> userUuids) {
        return userUuids.stream()
                .allMatch(repository::existsUserByUserUuid);
    }

    public Boolean existsApplicationByApplicationUuidAndEnabled(UUID applicationUuid) {
        return repository.existsApplicationByApplicationUuidAndEnabled(applicationUuid);
    }

    public Boolean existsApplicationByApplicationUuid(UUID applicationUuid) {
        return repository.existsApplicationByApplicationUuid(applicationUuid);
    }

    public List<UUID> findAllUserUuidExistInApplication(UUID applicationUuid) {
        return repository.findAllUserUuidByApplicationUuid(applicationUuid);
    }

    public Boolean existsUserInApplication(UUID applicationUuid) {
        return repository.existUserInApplication(applicationUuid);
    }
}
