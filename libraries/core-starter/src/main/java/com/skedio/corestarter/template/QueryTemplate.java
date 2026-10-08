package com.skedio.corestarter.template;

public interface QueryTemplate<A, R> {
    R handle(A query);
}
