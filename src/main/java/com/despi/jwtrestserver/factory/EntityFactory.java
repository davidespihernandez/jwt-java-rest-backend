package com.despi.jwtrestserver.factory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

public abstract class EntityFactory<T> {
	@Autowired protected EntityManager entityManager;

	public abstract T build();
	public T create(T entity, Consumer<T> consumer) {
		consumer.accept(entity);
		entityManager.persist(entity);
		return entity;
	}
}
