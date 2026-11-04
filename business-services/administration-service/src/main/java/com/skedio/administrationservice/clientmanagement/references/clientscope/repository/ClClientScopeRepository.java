package com.skedio.administrationservice.clientmanagement.references.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.ClClientScope;
import com.skedio.administrationservice.clientmanagement.references.clientscope.domain.argument.ClientScopeSearch;
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
public class ClClientScopeRepository {
    ClClientScopeJpaRepository repository;
    ClClientScopeEntityMapper entityMapper;
    ClClientScopeDaoMapper daoMapper;

    public Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid) {
        return repository.existsClientByClientUuidAndEnabledTrue(clientUuid);
    }

    public Boolean existsClientByClientUuid(UUID clientUuid) {
        return repository.existsClientByClientUuid(clientUuid);
    }

    public Boolean existsByClientUuidAndScopeUuidIn(UUID clientUuid, List<UUID> scopeUuids) {
        return repository.existsByClientUuidAndScopeUuidIn(clientUuid, scopeUuids);
    }

    public List<ClClientScope> saveAll(List<ClClientScope> clClientScopes) {
        List<ClClientScopeJpaEntity> entities = entityMapper.toEntity(clClientScopes);
        List<ClClientScopeJpaEntity> saved = repository.saveAll(entities);
        return entityMapper.toDomain(saved);
    }

    public Page<ClClientScope> findAll(ClientScopeSearch search, PageRequest pageRequest) {
        return repository.findAllDao(search, pageRequest)
                .map(daoMapper::toDomain);
    }

    public void deleteAllByClientUuid(UUID clientUuid) {
        repository.deleteAllByClientUuid(clientUuid);
    }

    public Boolean existsAllScopeUuid(List<UUID> scopeUuids) {
        return scopeUuids.stream()
                .allMatch(repository::existsScopeByScopeUuid);
    }
}
