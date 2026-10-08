package com.skedio.corestarter.template;

public interface CommandTemplate<A, R> {
    R handle(A command);
}
