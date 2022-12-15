package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Root interface for all DAO classes
 * @author Anida Nezovic
 */

public interface Dao<T> {

    /**
     * get entity from database based on given ID
     * @param id primary key of the entity
     * @return Entity from database
     */
    T getById(int id);

    /**
     * save entity into database
     * @param item item that we want to save into database
     * @return saved item
     */

    T add(T item);

    /**
     * update entity that is in database based on its id match
     * @param item item that we want to update
     * @return updated version of the given item
     */
    T update(T item);

    /**
     * delete item from database with given id
     * @param id primary key of entity
     */
    void delete(int id);

    /**
     * list all entities from database
     * @return list of all entities from database
     */
    List<T> getAll();

}