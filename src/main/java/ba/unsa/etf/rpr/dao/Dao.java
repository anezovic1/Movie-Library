package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

/**
 * Root interface for all DAO classes
 * @author anida
 */

public interface Dao<T> {

    /**
     * get entity from database based on given ID
     * @param id primary key of the entity
     * @return Entity from database
     */
    T getById(int id) throws MovieException;

    /**
     * save entity into database
     * @param item item that we want to save into database
     * @return saved item
     */

    T add(T item) throws MovieException;

    /**
     * update entity that is in database based on its id match
     * @param item item that we want to update
     * @return updated version of the given item
     */
    T update(T item) throws MovieException;

    /**
     * delete item from database with given id
     * @param id primary key of entity
     */
    void delete(int id) throws MovieException;

    /**
     * list all entities from database
     * @return list of all entities from database
     */
    List<T> getAll() throws MovieException;

}