package controller;

import model.User;
import model.dto.UserDTO;
import model.validation.Notification;
import service.activity.ActivityService;
import service.user.UserService;
import view.AdminView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.EMPLOYEE;

public class AdminController {

    private final AdminView adminView;
    private final UserService userService;
    private final ActivityService activityService;
    private final LoginController loginController;

    public AdminController(AdminView adminView, UserService userService, ActivityService activityService, LoginController loginController) {
        this.adminView = adminView;
        this.userService = userService;
        this.activityService = activityService;
        this.loginController = loginController;
        adminView.setCreateEmployeeButton(new CreateUserListener());
        adminView.setViewAllEmployeesButton(new ViewUsersListener());
        adminView.setDeleteEmployeeButton(new DeleteUserListener());
        adminView.setUpdateEmployeeButton(new UpdateUserListener());
        adminView.setGenerateActivityButton(new GenerateActivityListener());
        adminView.setLogoutButtonListener(new LogoutListener());
    }

    private void setVisible(boolean visible){
        adminView.setVisible(visible);
    }

    private class ViewUsersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateTable();
        }
    }

    private class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean employeeRole = adminView.getEmployeeCheckBox();
            boolean adminRole = adminView.getAdminCheckBox();

            if(employeeRole && adminRole) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), "You can't select both roles!");
            } else {
                Notification<Boolean> notification = new Notification<>();
                if (!employeeRole && !adminRole){
                    notification.addError("User must have a role!");

                } else {
                    UserDTO userDTO = adminView.getUserDTO();
                    if(employeeRole)
                        userDTO.setRole(EMPLOYEE);
                    else
                        userDTO.setRole(ADMINISTRATOR);
                    notification = userService.createEmployee(userDTO);
                }
                if (!notification.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User created!");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), notification.getFormattedErrors());
                }
            }
        }
    }

    private class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (userService.removeUser(adminView.getUsername())){
                JOptionPane.showMessageDialog(adminView.getContentPane(), "User deleted!");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(adminView.getContentPane(), "No user with that name!");
            }
        }
    }

    private class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean employeeRole = adminView.getEmployeeCheckBox();
            boolean adminRole = adminView.getAdminCheckBox();

            if(employeeRole && adminRole) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), "You can't select both roles!");
            } else {
                Notification<Boolean> notification = new Notification<>();
                if (!employeeRole && !adminRole){
                    notification.addError("User must have a role!");

                } else {
                    UserDTO userDTO = adminView.getUserDTO();
                    userDTO.setId(userService.findByUsername(userDTO.getUsername()).getResult().getId());
                    if(employeeRole)
                        userDTO.setRole(EMPLOYEE);
                    else
                        userDTO.setRole(ADMINISTRATOR);
                    notification = userService.updateEmployee(userDTO);
                }
                if (!notification.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User updated!");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), notification.getFormattedErrors());
                }
            }
        }
    }

    private class GenerateActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // :(
        }
    }

    private void updateTable(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("username");
        tableModel.addColumn("password");
        tableModel.addColumn("role_id");
        for (User user: userService.view()){
            Object[] objects = { user.getId(), user.getUsername(), user.getPassword(), user.getRole().getId()};
            tableModel.addRow(objects);
        }
        adminView.getEmployeeTable().setModel(tableModel);
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginController.setVisible(true);
            adminView.setVisible(false);
        }
    }

}
