package repository.user;

import model.User;
import model.validation.Notification;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword (String username, String password);

    Notification<User> findByUsername (String username);

    boolean save(User user);

    boolean updatePassword (User user, String newPassword);

    boolean deleteUser(String username);

    void removeAll();

}
