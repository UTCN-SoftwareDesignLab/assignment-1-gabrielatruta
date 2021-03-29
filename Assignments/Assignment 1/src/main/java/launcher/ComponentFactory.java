package launcher;

import controller.LoginController;
import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RoleRepository;
import repository.security.RoleRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.activity.ActivitySerivce;
import service.activity.ActivityServiceMySQL;
import service.admin.AdminService;
import service.admin.AdminServiceMySQL;
import service.employee.EmployeeService;
import service.employee.EmployeeServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import view.LoginView;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final LoginView loginView;

    private final LoginController loginController;

    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;
    private final AdminService adminService;
    private final ActivitySerivce activitySerivce;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;


    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();

        this.roleRepository = new RoleRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, roleRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.activityRepository = new ActivityRepositoryMySQL(connection,userRepository);

        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.roleRepository);
        this.employeeService = new EmployeeServiceMySQL(this.accountRepository, this.clientRepository);
        this.adminService = new AdminServiceMySQL(this.userRepository);
        this.activitySerivce = new ActivityServiceMySQL(this.activityRepository);

        this.loginView = new LoginView();
        this.loginController = new LoginController(loginView, authenticationService);

    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public AdminService getAdminService(){return adminService;}

    public EmployeeService getEmployeeService(){return employeeService;}

    public ActivitySerivce getActivitySerivce() { return activitySerivce;}

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleRepository getRolesRepository() {
        return roleRepository;
    }

    public ClientRepository getClientRepository(){
        return clientRepository;
    }

    public AccountRepository getAccountRepository(){ return accountRepository;}

    public ActivityRepository getActivityRepository(){ return activityRepository;}

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
