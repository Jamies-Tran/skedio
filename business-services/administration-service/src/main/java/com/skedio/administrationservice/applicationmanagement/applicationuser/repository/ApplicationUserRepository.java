package com.skedio.administrationservice.applicationmanagement.applicationuser.repository;

import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.ApplicationUser;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationGroupGetUser;
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
public class ApplicationUserRepository {
    ApplicationUserJpaRepository repository;
    ApplicationUserDaoMapper daoMapper;
    ApplicationUserEntityMapper entityMapper;

    public void saveAll(List<ApplicationUser> applicationUsers) {
        List<ApplicationUserJpaEntity> entities = entityMapper.toEntity(applicationUsers);
        repository.saveAll(entities);
    }

    public Page<ApplicationUser> findUserInApplication(ApplicationGroupGetUser search, PageRequest pageRequest) {
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

    public List<UUID> findAllUserUuidExistInApplication(UUID applicationUuid) {
        return repository.findAllUserUuidByApplicationUuid(applicationUuid);
    }

    public Boolean existsUserInApplication(UUID applicationUuid) {
        return repository.existUserInApplication(applicationUuid);
    }
}
