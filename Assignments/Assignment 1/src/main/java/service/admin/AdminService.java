package service.admin;

import model.User;
import model.validation.Notification;

import java.util.List;

public interface AdminService {

    Notification<Boolean> createEmployee(User user);

    List<User> view();

    Notification<Boolean> updatePassword (User user, String newPassword);

    boolean removeUser(String username);

}
