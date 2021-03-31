package controller;

import model.validation.Notification;
import service.account.AccountService;
import service.client.ClientService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {

    private final EmployeeView employeeView;
    private final LoginController loginController;
    private final AccountService accountService;
    private final ClientService clientService;

    public EmployeeController(EmployeeView employeeView, LoginController loginController, AccountService accountService, ClientService clientService) {
        this.employeeView = employeeView;
        this.loginController = loginController;
        this.accountService = accountService;
        this.clientService = clientService;
        employeeView.setCreateClientButtonListener(new CreateClientListener());
        employeeView.setViewClientsButtonListener(new ViewClientListener());
        employeeView.setUpdateClientButtonListener(new UpdateClientListener());
        employeeView.setCreateAccountButtonListener(new CreateAccountListener());
        employeeView.setDeleteAccountButtonListener(new DeleteAccountListener());
        employeeView.setUpdateAccountButtonListener(new UpdateAccountListener());
        employeeView.setViewAccountsButtonListener(new ViewAccountListener());
        employeeView.setTransferMoneyButtonListener(new TransferMoneyListener());
        employeeView.setLogoutButtonListener(new LogoutButtonListener());
    }

    private class CreateClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Boolean> notification = clientService.createClient(employeeView.getClientDTO());
            if (!notification.hasErrors())
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client created!");
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), notification.getFormattedErrors());

        }
    }

    private class ViewClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //  :(

        }
    }

    private class UpdateClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Boolean> notification = clientService.updateClient(employeeView.getClientDTO());

            if(notification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), notification.getFormattedErrors());
            }
            else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client updated!");
            }
        }
    }

    private class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class TransferMoneyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginController.setVisible(true);
            employeeView.setVisible(false);
        }
    }
}
