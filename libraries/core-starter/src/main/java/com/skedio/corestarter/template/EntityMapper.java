package com.skedio.corestarter.template;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<Entity, Domain> {
    Entity toEntity(Domain domain);
    Domain toDomain(Entity entity);
    List<Entity> toEntity(List<Domain> domainList);
    List<Domain> toDomain(List<Entity> entityList);
    void update(@MappingTarget Entity entity, Domain domain);
}
