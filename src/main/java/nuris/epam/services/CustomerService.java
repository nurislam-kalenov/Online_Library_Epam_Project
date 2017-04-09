package nuris.epam.services;

import nuris.epam.dao.*;
import nuris.epam.dao.exception.DaoException;
import nuris.epam.dao.manager.DaoFactory;
import nuris.epam.entity.*;
import nuris.epam.services.exception.ServiceException;
import nuris.epam.utils.SqlDate;

import java.util.List;

/**
 * Created by User on 21.03.2017.
 */
public class CustomerService {
    private String USER_ROLE = "user";

    public void registerCustomer(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
                CustomerRole customerRole = customerRoleDao.findRoleByName(USER_ROLE);

                daoFactory.startTransaction();
                personDao.insert(customer.getPerson());
                customer.setCustomerRole(customerRole);
                customer.setRegisterDate(SqlDate.currentDateAndTime());
                customerDao.insert(customer);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("can't rollback transaction", e);
                }
                throw new ServiceException("can't register customer", e);
            }
        }
    }

    public Customer findByLogin(String login) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            Customer customer;
            try {
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                customer = customerDao.getCustomer(login);
                fillCustomer(customer);
                return customer;
            } catch (DaoException e) {
                throw new ServiceException("can't find by login customer", e);
            }
        }
    }

    public Customer findCustomerById(int id) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            Customer customer;
            try {
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                customer = customerDao.findById(id);
                fillCustomer(customer);
                return customer;
            } catch (DaoException e) {
                throw new ServiceException("can't find by customer id customer", e);
            }
        }
    }
    public Customer findByLoginPassword(String login, String password) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            Customer customer;
            try {
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                customer = customerDao.getCustomer(login, password);
                fillCustomer(customer);
                return customer;
            } catch (DaoException e) {
                throw new ServiceException("can't find by login and password customer", e);
            }
        }
    }

    public void updateCustomer(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                Person person = personDao.findByCustomer(customer);
                customer.setPerson(person);
                customerDao.update(customer);
            } catch (DaoException e) {
                throw new ServiceException("can't update customer ", e);
            }
        }
    }

    public void updatePerson(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                personDao.update(customer.getPerson());
            } catch (DaoException e) {
                throw new ServiceException("can't update person", e);
            }
        }
    }

    public void deleteCustomer(Customer customer) throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                Person person = personDao.findByCustomer(customer);

                daoFactory.startTransaction();
                customerDao.delete(customer);
                personDao.delete(person);
                daoFactory.commitTransaction();

            } catch (DaoException e) {
                try {
                    daoFactory.rollbackTransaction();
                } catch (DaoException e1) {
                    throw new ServiceException("can't rollback transaction", e);
                }
                throw new ServiceException("can't delete customer", e);
            }
        }
    }

    private void fillCustomer(Customer customer) throws ServiceException {
        try {
            if (customer != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {

                    PersonDao personDao = (PersonDao) daoFactory.getDao(daoFactory.typeDao().getPersonDao());
                    CustomerRoleDao customerRoleDao = (CustomerRoleDao) daoFactory.getDao(daoFactory.typeDao().getCustomerRoleDao());
                    CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());

                    Person person = personDao.findByCustomer(customer);
                    person.setCity(cityDao.findByPerson(person));
                    customer.setPerson(person);
                    customer.setCustomerRole(customerRoleDao.findByCustomer(customer));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't fill customer ", e);
        }
    }

    public List<City> getAllCity() throws ServiceException {
        List<City> list;
        try {
            try (DaoFactory daoFactory = new DaoFactory()) {
                CityDao cityDao = (CityDao) daoFactory.getDao(daoFactory.typeDao().getCityDao());
                list = cityDao.getAll();
                return list;
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't get all city", e);
        }
    }

    public int customerCount() throws ServiceException {
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                CustomerDao customerDao = (CustomerDao) daoFactory.getDao(daoFactory.typeDao().getCustomerDao());
                int count = customerDao.getCustomerCount();
                return count;
            } catch (DaoException e) {
                throw new ServiceException("can't get count customer", e);
            }
        }
    }

    public boolean isLoginAvalible(String login) throws ServiceException {
        if (findByLogin(login) != null) {
            return false;
        } else {
            return true;
        }
    }
    public void uploadAvatar(Avatar avatar) throws ServiceException{
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AvatarDao avatarDao = (AvatarDao) daoFactory.getDao(daoFactory.typeDao().getAvatarDao());
                avatarDao.insert(avatar);
            } catch (DaoException e) {
                throw new ServiceException("can't upload avatar", e);
            }
        }
    }

    public Avatar findByIdAvatar(int id) throws ServiceException{
        Avatar avatar = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                AvatarDao avatarDao = (AvatarDao) daoFactory.getDao(daoFactory.typeDao().getAvatarDao());
             avatar =  avatarDao.findById(id);
            } catch (DaoException e) {
                throw new ServiceException("can't upload avatar", e);
            }
        }
        return avatar;
    }
}
