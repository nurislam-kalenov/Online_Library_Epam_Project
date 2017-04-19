package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.BaseEntity;

/**
 * Интерфейс , описовает СRUD операций для SQL заросов.
 *
 * @author Kalenov Nurislam
 */
public interface Dao<T extends BaseEntity> {
    /**
     * Запоос на создание поле.
     *
     * @param item - Дочерние классы BaseEntity
     * @return Возаращает дочерний класс абстрактного класса BaseEntity.
     */
    T insert(T item) throws DaoException;

    /**
     * Запоос на чтение поле.
     *
     * @param id - уникальное значение поле
     * @return Возаращает дочерний класс абстрактного класса BaseEntity.
     */
    T findById(int id) throws DaoException;

    /**
     * Запоос на обнавление поле.
     *
     * @param item - Дочерние классы BaseEntity
     */
    void update(T item) throws DaoException;

    /**
     * Запоос на удаление поле.
     *
     * @param item - Дочерние классы BaseEntity
     */
    void delete(T item) throws DaoException;

}
