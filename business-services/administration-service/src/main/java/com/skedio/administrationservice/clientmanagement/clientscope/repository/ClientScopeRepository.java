package com.skedio.administrationservice.clientmanagement.clientscope.repository;

import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeSearch;
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
public class ClientScopeRepository {
    ClientScopeJpaRepository repository;
    ClientScopeEntityMapper entityMapper;
    ClientScopeDaoMapper daoMapper;

    public Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid) {
        return repository.existsClientByClientUuidAndEnabledTrue(clientUuid);
    }

    public Boolean existsByClientUuidAndScopeUuidIn(UUID clientUuid, List<UUID> scopeUuids) {
        return repository.existsByClientUuidAndScopeUuidIn(clientUuid, scopeUuids);
    }

    public List<ClientScope> saveAll(List<ClientScope> clientScopes) {
        List<ClientScopeJpaEntity> entities = entityMapper.toEntity(clientScopes);
        List<ClientScopeJpaEntity> saved = repository.saveAll(entities);
        return entityMapper.toDomain(saved);
    }

    public Page<ClientScope> findAll(ClientScopeSearch search, PageRequest pageRequest) {
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
