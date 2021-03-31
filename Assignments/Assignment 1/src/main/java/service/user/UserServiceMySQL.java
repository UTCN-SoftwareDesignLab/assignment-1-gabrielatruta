package service.user;

import model.Role;
import model.User;
import model.builder.RoleBuilder;
import model.builder.UserBuilder;
import model.dto.UserDTO;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.user.UserRepository;

import java.util.List;

public class UserServiceMySQL implements UserService {
    private final UserRepository userRepository;

    public UserServiceMySQL(UserRepository userRepository) {
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
    public Notification<Boolean> updateEmployee(User user) {
        UserValidator userValidator = new UserValidator(user);
        boolean isValid = userValidator.validate();
        Notification<Boolean> notification = new Notification<>();

        if(!isValid){
            userValidator.getErrors().forEach(notification::addError);
            notification.setResult(Boolean.FALSE);
        } else
            notification.setResult(userRepository.updateUser(user).getResult());
        return notification;
    }

    @Override
    public Notification<Boolean> updateEmployee(UserDTO userDTO) {
        Notification<Boolean> notification = new Notification<>();
        Role role = new RoleBuilder()
                .setID(-1L)
                .setRole("employee")
                .build();
        User user = new UserBuilder()
                .setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword())
                .setRole(role)
                .build();
        UserValidator userValidator = new UserValidator(user);
        if (userValidator.validate()){
            notification = userRepository.updateUser(user);
        } else {
            notification.addError(userValidator.getErrors().get(0));
        }
        return  notification;
    }

    @Override
    public Notification<User> findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public Notification<Boolean> createEmployee(UserDTO userDTO) {
        Notification<Boolean> notification = new Notification<>();

        Role role = new RoleBuilder()
                .setID(-1L)
                .setRole(userDTO.getRole())
                .build();
        User user = new UserBuilder()
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword())
                .setRole(role)
                .build();
        UserValidator userValidator = new UserValidator(user);
        if(userValidator.validate()){
            notification.setResult(userRepository.save(user));
            return notification;
        } else {
            notification.addError(userValidator.getErrors().get(0));
        }
        return notification;
    }

    @Override
    public boolean removeUser(String username) {
       return userRepository.deleteUser(username);
    }


}
