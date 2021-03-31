package repository.user;

import model.User;
import model.dto.UserDTO;
import model.validation.Notification;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword (String username, String password);

    Notification<User> findByUsername (String username);

    boolean save(User user);

    Notification<Boolean> updateUser(User user);

    Notification<Boolean> updateUser(UserDTO userDTO);

    boolean deleteUser(String username);

    void removeAll();

}
