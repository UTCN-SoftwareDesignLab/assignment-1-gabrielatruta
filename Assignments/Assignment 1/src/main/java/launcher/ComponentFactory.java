package launcher;

import controller.AdminController;
import controller.EmployeeController;
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
import service.activity.ActivityService;
import service.activity.ActivityServiceMySQL;
import service.user.UserService;
import service.user.UserServiceMySQL;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import view.AdminView;
import view.EmployeeView;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final view.LoginView loginView;
    private final AdminView adminView;
    private final EmployeeView employeeView;

    private final LoginController loginController;
    private final AdminController adminController;
    private final EmployeeController employeeController;

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ActivityService activityService;
    private final ClientService clientService;
    private final AccountService accountService;

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
        this.userService = new UserServiceMySQL(this.userRepository);
        this.activityService = new ActivityServiceMySQL(this.activityRepository);
        this.clientService = new ClientServiceMySQL(this.clientRepository);
        this.accountService = new AccountServiceMySQL(this.accountRepository);

        this.loginView = new view.LoginView();
        this.adminView = new AdminView();
        this.employeeView = new EmployeeView();

        this.loginController = new LoginController(loginView, authenticationService, adminView, activityService, employeeView);
        this.adminController = new AdminController(adminView, userService, activityService, loginController);
        this.employeeController = new EmployeeController(employeeView, loginController, accountService, clientService);

    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserService getUserService(){return userService;}

    public ActivityService getActivityService() { return activityService;}

    public ClientService getClientService(){ return  clientService;}

    public AccountService getAccountService() { return  accountService;}

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

    public view.LoginView getLoginView() {
        return loginView;
    }

    public AdminView getAdminView() {return adminView;}

    public EmployeeView getEmployeeView() {
         return employeeView;
    }
}
