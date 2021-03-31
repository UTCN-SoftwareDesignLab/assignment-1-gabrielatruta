package controller;

import model.Activity;
import model.User;
import model.builder.ActivityBuilder;
import model.validation.Notification;
import service.activity.ActivityService;
import service.user.AuthenticationService;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import static database.Constants.Roles.*;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
    private final view.LoginView loginView;
    private final AuthenticationService authenticationService;
    private final AdminView adminView;
    private final ActivityService activitySerivce;
    private final EmployeeView employeeView;

    public LoginController(view.LoginView loginView, AuthenticationService authenticationService, AdminView adminView, ActivityService activitySerivce, EmployeeView employeeView) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.adminView = adminView;
        this.activitySerivce = activitySerivce;
        this.employeeView = employeeView;
        loginView.setLoginButtonListener(new LoginButtonListener());
    }

    public void setVisible (boolean visible){
        loginView.setVisible(visible);
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");

                if (loginNotification.getResult().getRole().getName().equals(ADMINISTRATOR)){
                    Activity activity = new ActivityBuilder()
                            .setID(-1L)
                            .setEmployeeID(loginNotification.getResult().getId())
                            .setActivity("Logged in")
                            .setDate(Date.valueOf(LocalDate.now()))
                            .build();
                    activitySerivce.addActivity(activity);
                    adminView.setVisible();
                } else if (loginNotification.getResult().getRole().getName().equals(EMPLOYEE)){
                    Activity activity = new ActivityBuilder()
                            .setID(-1L)
                            .setEmployeeID(loginNotification.getResult().getId())
                            .setActivity("Logged in")
                            .setDate(Date.valueOf(LocalDate.now()))
                            .build();
                    activitySerivce.addActivity(activity);
                    employeeView.setVisible();
                }

            }

        }
    }

}
