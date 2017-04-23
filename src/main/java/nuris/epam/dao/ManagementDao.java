package nuris.epam.dao;

import nuris.epam.dao.exception.DaoException;
import nuris.epam.entity.Management;
import nuris.epam.entity.Transaction;

import java.util.List;

/**
 * Абстрактный класс, описывает дополнительные запросы для таблицы management в БД.
 *
 * @author Kalenonov Nurislam
 */
public abstract class ManagementDao extends BaseDao<Management> {

    /**
     * Метод, возвращяет n-ое количество сущности Management c учетом его состояние(активный, неактивный).
     *
     * @param start    - начало поле в талице в БД
     * @param count    - количесвто поле в БД, которую необходимо прогрузить.
     * @param isActive - состояние сущностей , может быть активным или неактивным
     * @return Возвращает конкретное число книг
     * @throws DaoException
     */
    public abstract List<Management> getListManagement(int start, int count, boolean isActive) throws DaoException;

    /**
     * Метод, прелоставляет список сущностей Management, найденных с учетом сущности Transaction.
     *
     * @param transaction - сущность
     * @return Возвращаяет список сущностей Management
     * @throws DaoException
     */
    public abstract Management findByTransaction(Transaction transaction) throws DaoException;

    /**
     * Метод, прелоставляет общее количество сущностей Management c учетом их состаяние(активный, неактивный).
     *
     * @param isActive - состояние сущности Management
     * @return Возвращаяет список сущностей Management
     * @throws DaoException
     */
    public abstract int getManagementCount(boolean isActive) throws DaoException;
}
