package service.admin;

import model.Activity;
import model.User;
import model.validation.Notification;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    Notification<Boolean> createEmployee(User user);

    List<User> view();

    Notification<Boolean> updatePassword (User user, String newPassword);

    boolean removeUser(String username);

}
