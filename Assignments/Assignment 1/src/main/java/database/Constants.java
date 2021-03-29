package database;

import model.Role;
import model.User;
import model.builder.RoleBuilder;
import model.builder.UserBuilder;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

public class Constants {
    public static class Schemas {
        public static final String TEST = "assignment1_test";
        public static final String PRODUCTION = "assignment1";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String ACCOUNT = "account";
        public static final String CLIENT = "client";
        public static final String ROLE = "role";
        public static final String USER = "user";
        // public static final String REPORT = "report";

        //public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{ACCOUNT, CLIENT, USER, ROLE, REPORT};
        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{ROLE, USER, CLIENT, ACCOUNT};

    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }

    public static class Users {
        private static final Role admin = new RoleBuilder()
                .setID(-1L)
                .setRole("administrator")
                .build();
        public static final User ADMIN = new UserBuilder()
                .setId(-1L)
                .setUsername("gabriela.truta99@yahoo.com")
                .setPassword(AuthenticationServiceMySQL.encodePassword("gabrielaTruta3!"))
                .setRole(admin)
                .build();

        public static final User[] USERS = new User[]{ADMIN};
    }
}
