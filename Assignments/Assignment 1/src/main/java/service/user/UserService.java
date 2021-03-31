package service.user;

import model.User;
import model.dto.UserDTO;
import model.validation.Notification;
import java.util.List;

public interface UserService {

    Notification<Boolean> createEmployee(User user);

    Notification<Boolean> createEmployee(UserDTO userDTO);

    List<User> view();

    Notification<Boolean> updateEmployee (User user);

    Notification<Boolean> updateEmployee (UserDTO userDTO);

    Notification<User> findByUsername (String user);

    boolean removeUser(String username);

}
