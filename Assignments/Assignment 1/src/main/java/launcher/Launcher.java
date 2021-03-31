package launcher;

import database.Bootstraper;
import database.DBConnectionFactory;

import java.sql.SQLException;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static boolean BOOTSTRAP = true;

    public static void main(String[] args) throws SQLException {

        boolean databaseWorks = new DBConnectionFactory().getConnectionWrapper(false).testConnection();
        System.out.println("database works: " + databaseWorks);

        bootstrap();

        ComponentFactory componentFactory = ComponentFactory.instance(false);
        componentFactory.getLoginView().setVisible();

    }

    private static void bootstrap() {
        if (BOOTSTRAP) {
            try {
                new Bootstraper().execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
