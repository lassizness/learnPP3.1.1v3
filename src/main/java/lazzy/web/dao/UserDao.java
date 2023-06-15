package lazzy.web.dao;

import lazzy.web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUser(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
