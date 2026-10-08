package com.skedio.corestarter.template;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class Query<A, R> implements QueryTemplate<A, R> {
}
