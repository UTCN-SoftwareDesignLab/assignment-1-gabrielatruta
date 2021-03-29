package repository.security;

import model.Role;
import model.validation.Notification;

import java.sql.SQLException;
import java.util.List;

public interface RoleRepository {

    List<Role> findAll();

    Notification<Role> findRoleByName (String name);

    boolean addRole (String role);

    Notification<Role> findRoleById(long role_id);

    boolean deleteRole (String name);

    void removeAll();

}
