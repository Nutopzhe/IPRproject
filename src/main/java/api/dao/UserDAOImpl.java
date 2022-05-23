package api.dao;

import api.entity.User;
import api.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //получить всех пользователей и заполнить список users
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            users = session.createQuery(criteriaQuery).getResultList();

            session.getTransaction().commit();

            for (User user : users) {
                if (user.getSex().equals("t")) {
                    user.setSex("MALE");
                }
                if (user.getSex().equals("f")) {
                    user.setSex("FEMALE");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении всех пользователей!");
        }
        return users;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //получить пользователя по Id
            user = session.load(User.class, userId);
            session.getTransaction().commit();

            if (user.getSex().equals("t")) {
                user.setSex("MALE");
            }
            if (user.getSex().equals("f")) {
                user.setSex("FEMALE");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении пользователя по Id!");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            //добавить пользователя в таблицу
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Ошибка при вставке нового пользователя!");
        }
    }
}