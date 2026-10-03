package com.skedio.corestarter.template;

import java.util.List;

public interface DomainMapper<Domain, Dto> {
    Domain toDomain(Dto dto);
    Dto toDto(Domain domain);
    List<Domain> toDomain(List<Dto> dtoList);
    List<Dto> toDto(List<Domain> domainList);
}
