package com.skedio.administrationservice.applicationmanagement.applications.repository;

import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupSearch;
import com.skedio.administrationservice.applicationmanagement.applicationuser.repository.ApplicationUserDaoMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupRepository {
    ApplicationGroupJpaRepository repository;
    ApplicationGroupEntityMapper mapper;
    ApplicationUserDaoMapper daoMapper;

    public List<ApplicationGroup> saveAll(List<ApplicationGroup> applicationGroups) {
        List<ApplicationGroupJpaEntity> entities = mapper.toEntity(applicationGroups);
        List<ApplicationGroupJpaEntity> savedList = repository.saveAll(entities);
        return mapper.toDomain(savedList);
    }

    public ApplicationGroup save(ApplicationGroup applicationGroup) {
        ApplicationGroupJpaEntity entity = mapper.toEntity(applicationGroup);
        ApplicationGroupJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    public Boolean existsByApplicationGroupNameIn(List<String> applicationGroupNames) {
        return repository.existsByApplicationNameIn(applicationGroupNames);
    }

    public Page<ApplicationGroup> findAll(ApplicationGroupSearch search, Pageable pageable) {
        return repository.findAll(search, pageable)
                .map(mapper::toDomain);
    }

    public Optional<ApplicationGroup> findByUuid(UUID applicationGroupUuid) {
        return repository.findByApplicationUuid(applicationGroupUuid)
                .map(mapper::toDomain);
    }

    public Boolean existsByApplicationName(String applicationName) {
        return repository.existsByApplicationName(applicationName);
    }

    public void delete(UUID applicationGroupUuid) {
        repository.deleteByApplicationUuid(applicationGroupUuid);
    }
}
