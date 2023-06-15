package lazzy.web.dao;

import org.springframework.stereotype.Repository;
import lazzy.web.entity.UserEntity;
import lazzy.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<UserEntity> usersEntity = entityManager.createQuery("from UserEntity", UserEntity.class).getResultList();
        for (UserEntity userEntity : usersEntity) {
            users.add(new User(userEntity.getId(), userEntity.getName(), userEntity.getAge()));
        }
        return users;
    }

    @Override
    public User getUser(long id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        User user = null;
        if (userEntity != null) {
            user = new User(userEntity.getId(), userEntity.getName(), userEntity.getAge());
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = new UserEntity(user.getName(), user.getAge());
        entityManager.persist(userEntity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = entityManager.find(UserEntity.class, user.getId());
        if (userEntity != null) {
            userEntity.setName(user.getName());
            userEntity.setAge(user.getAge());
            entityManager.merge(userEntity);
        }
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        if (userEntity != null) {
            entityManager.remove(userEntity);
        }
    }
}
