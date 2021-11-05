package com.labor.Repository;

import java.util.List;

public abstract class InMemoryRepository<T> implements ICrudRepository<T> {
    protected List<T> objectList;

    public InMemoryRepository(List<T> objectList) {

        this.objectList = objectList;
    }

    @Override
    public Iterable<T> findAll() {

        return this.objectList;
    }

}
