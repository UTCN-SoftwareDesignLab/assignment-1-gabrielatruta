package service.admin;

import model.User;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.user.UserRepository;

import java.util.List;

public class AdminServiceMySQL implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceMySQL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Notification<Boolean> createEmployee(User user) {

        UserValidator userValidator = new UserValidator(user);
        boolean isValid = userValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!isValid){
            userValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
        } else
            notification.setResult(userRepository.save(user));
        return notification;
    }

    @Override
    public List<User> view() {
        return userRepository.findAll();
    }

    @Override
    public Notification<Boolean> updatePassword(User user, String newPassword) {
        UserValidator userValidator = new UserValidator(user);
        boolean isValid = userValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!isValid){
            userValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
        } else
            notification.setResult(userRepository.updatePassword(user, newPassword));
        return notification;
    }

    @Override
    public boolean removeUser(String username) {
       return userRepository.deleteUser(username);
    }
}
