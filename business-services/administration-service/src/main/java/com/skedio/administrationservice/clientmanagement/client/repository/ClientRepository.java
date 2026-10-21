package com.skedio.administrationservice.clientmanagement.client.repository;

import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.ClientSearch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientRepository {
    ClientJpaRepository repository;
    ClientEntityMapper entityMapper;

    public Boolean existsApplicationUuidAndEnabled(UUID applicationUuid) {
        return repository.existsApplicationByApplicationUuidAndEnabled(applicationUuid);
    }

    public Boolean existsByClientNameIn(List<String> clientNames) {
        return repository.existsByClientNameIn(clientNames);
    }

    public Boolean existByClientName(String clientName) {
        return repository.existsByClientName(clientName);
    }

    public Client save(Client client) {
        ClientJpaEntity entity = entityMapper.toEntity(client);
        ClientJpaEntity saved = repository.save(entity);
        return entityMapper.toDomain(saved);
    }

    public List<Client> saveAll(List<Client> clients) {
        List<ClientJpaEntity> entities = entityMapper.toEntity(clients);
        List<ClientJpaEntity> saved = repository.saveAll(entities);
        return entityMapper.toDomain(saved);
    }

    public Page<Client> findAll(ClientSearch search, PageRequest pageRequest) {
        return repository.findAll(search, pageRequest)
                .map(entityMapper::toDomain);
    }

    public Optional<Client> findByClientUuid(UUID clientUuid) {
        return repository.findByClientUuid(clientUuid)
                .map(entityMapper::toDomain);
    }

    public void deleteByClientUuid(UUID clientUuid) {
        repository.deleteByClientUuid(clientUuid);
    }

    public void deleteAllByApplicationUuid(UUID applicationUuid) {
        repository.deleteAllByApplicationUuid(applicationUuid);
    }
}
