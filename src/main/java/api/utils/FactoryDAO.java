package api.utils;

import api.dao.CarDAO;
import api.dao.UserDAO;
import api.dao.impl.CarDAOImpl;
import api.dao.impl.UserDAOImpl;

public class FactoryDAO {
    private static FactoryDAO instance;
    private static CarDAO carDAO;
    private static UserDAO userDAO;

    private FactoryDAO() {}

    public static FactoryDAO getInstance() {
        if (instance == null) {
            instance = new FactoryDAO();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public CarDAO getCarDAO() {
        if (carDAO == null) {
            carDAO = new CarDAOImpl();
        }
        return carDAO;
    }
}
