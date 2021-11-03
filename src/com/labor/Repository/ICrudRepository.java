package com.labor.Repository;

import com.labor.Exceptions.NullException;

import java.util.UUID;

public interface ICrudRepository<T> {
    /**
     * finds the object with id 'id'
     * @param id id of searched object
     * @return object with id or null if it doesn't exist
     * @throws NullException if input parameter id is NULL
     */
    T findOne(UUID id) throws NullException;


    /**
     * returns all stored objects
     * @return all stored objects
     */
    Iterable<T> findAll();


    /**
     * saves(adds) an object to repository
     * @param obj obj different from null
     * @return null if object is saved otherwise obje if id exists
     * @exception NullException if obj is NULL
     */
    T save(T obj) throws NullException;


    /**
     * modifies an object
     * @param obj obj different from null
     * @return null - null if object is updated otherwise obj if id doesn't exist
     * @exception NullException if obj is NULL
     */
    T update(T obj) throws NullException;


    /**
     * removes object with id 'id'
     * @param id id must be not null
     * @return obj or null obj id doesn't exist
     * @exception NullException if id is NULL
     */
    T delete(UUID id) throws NullException;

}
