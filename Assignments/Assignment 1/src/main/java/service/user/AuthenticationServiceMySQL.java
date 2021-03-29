package service.user;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;

import repository.security.RoleRepository;
import repository.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static database.Constants.Roles.EMPLOYEE;

/**
 * Created by Alex on 11/03/2017.
 */
public class AuthenticationServiceMySQL implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthenticationServiceMySQL(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean register(String username, String password) {
        String encodedPassword = encodePassword(password);

        Role role = roleRepository.findRoleByName(EMPLOYEE).getResult();

        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(encodedPassword)
                .setRole(role)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Notification<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, encodePassword(password));
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
