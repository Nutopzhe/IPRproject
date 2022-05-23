package api.dao;

import api.entity.User;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(Integer userId);
    void addUser(User user);
}
