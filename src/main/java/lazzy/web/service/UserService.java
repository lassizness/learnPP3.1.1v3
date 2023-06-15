package lazzy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lazzy.web.dao.UserDao;
import lazzy.web.model.User;

import java.util.List;

@Service
@Transactional
public class UserService{

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public User addUser(User user) {
        userDao.saveUser(user);
        return user;
    }


    public User updateUser(User user) {
        userDao.updateUser(user);

        return user;
    }


    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }


    public User getUser(long id) {
        return userDao.getUser(id);
    }


    public List<User> getAllUsers() {
        return userDao.getUsers();
    }
}
