package com.skedio.corestarter.template;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class Command<A, R> implements CommandTemplate<A, R> {
}
