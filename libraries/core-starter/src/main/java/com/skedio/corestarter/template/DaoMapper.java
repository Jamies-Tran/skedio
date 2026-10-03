package com.skedio.corestarter.template;

import java.util.List;

public interface DaoMapper<Dao, Domain> {
    Domain toDomain(Dao dao);
    List<Domain> toDomain(List<Dao> daoList);
}
