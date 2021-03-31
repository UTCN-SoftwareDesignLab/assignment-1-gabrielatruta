package view;

import model.dto.UserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Alex on 18/03/2017.
 */
public class AdminView extends JFrame {

    private JLabel adminViewLabel;

    private JButton createEmployeeButton;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel roleLabel;
    private JLabel employeeRoleLabel;
    private JLabel adminRoleLabel;
    private JCheckBox employeeRoleCheckbox;
    private JCheckBox adminRoleCheckbox;

    private JButton viewAllEmployeesButton;
    private JTable employeeTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    private JButton generateActivityButton;
    private JLabel startDateLabel;
    private JTextField startDateText;
    private JLabel endDateLabel;
    private JTextField endDateText;

    private JButton updateEmployee;
    private JButton deleteEmployeeButton;
    private JButton logoutButton;


    public AdminView() throws HeadlessException {
        setSize(700, 550);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        add(adminViewLabel);
        add(createEmployeeButton);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(employeeRoleLabel);
        add(adminRoleLabel);
        add(employeeRoleCheckbox);
        add(adminRoleCheckbox);
        add(viewAllEmployeesButton);
        add(employeeTable);
        add(scrollPane);
        add(updateEmployee);
        add(deleteEmployeeButton);
        add(generateActivityButton);
        add(logoutButton);
        add(roleLabel);
        add(startDateText);
        add(endDateText);
        add(startDateLabel);
        add(endDateLabel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        adminViewLabel = new JLabel("Admin view");
        adminViewLabel.setBounds(15, 5, 80, 40);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(430, 5, 100, 70);
        usernameField = new JTextField();
        usernameField.setBounds(430, 50, 200, 40);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(430,100,100,70);
        passwordField = new JTextField();
        passwordField.setBounds(430, 145, 200, 40);

        roleLabel = new JLabel("Role: ");
        roleLabel.setBounds(430, 175, 100, 70);
        employeeRoleLabel = new JLabel("employee");
        employeeRoleLabel.setBounds(430, 210, 100, 40 );
        employeeRoleCheckbox = new JCheckBox();
        employeeRoleCheckbox.setBounds(490, 210, 40, 40);
        adminRoleLabel = new JLabel("admin");
        adminRoleLabel.setBounds(570, 210, 80, 40);
        adminRoleCheckbox = new JCheckBox();
        adminRoleCheckbox.setBounds(610, 210, 40, 40);

        startDateLabel = new JLabel("Start date: ");
        startDateLabel.setBounds(430, 350, 100, 40);
        startDateText = new JTextField();
        startDateText.setText("18/03/2021");
        startDateText.setBounds(550, 350, 80, 40);

        endDateLabel = new JLabel("End date: ");
        endDateLabel.setBounds(430, 400, 100, 40);
        endDateText = new JTextField();
        endDateText.setText("28/03/2021");
        endDateText.setBounds(550,400, 80, 40);

        createEmployeeButton = new JButton("Create");
        createEmployeeButton.setBounds(15, 350, 100, 40);

        viewAllEmployeesButton = new JButton("View all");
        viewAllEmployeesButton.setBounds(165, 350, 100, 40);

        deleteEmployeeButton = new JButton("Delete");
        deleteEmployeeButton.setBounds(315, 350, 100, 40);

        updateEmployee = new JButton("Update");
        updateEmployee.setBounds(15, 400, 100, 40);

        generateActivityButton = new JButton("Activity");
        generateActivityButton.setBounds(165, 400, 100, 40);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(315, 400, 100, 40);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 35, 400, 200);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("username");
        tableModel.addColumn("password");
        tableModel.addColumn("role_id");

        employeeTable = new JTable();
        employeeTable.setModel(tableModel);
        scrollPane.setViewportView(employeeTable);

    }

    public UserDTO getUserDTO(){
        return new UserDTO(usernameField.getText(), passwordField.getText());
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setUsernameText(String username) { usernameField.setText(username);}

    public void setPasswordText(String password) { passwordField.setText(password);}

    public boolean getEmployeeCheckBox() {return employeeRoleCheckbox.isSelected();}

    public boolean getAdminCheckBox() { return adminRoleCheckbox.isSelected();}

    public DefaultTableModel getTableModel(){
        return this.tableModel;
    }

    public JTable getEmployeeTable(){
        return this.employeeTable;
    }

    public void setLogoutButtonListener(ActionListener logoutButtonListener) {
        logoutButton.addActionListener(logoutButtonListener);
    }

    public void setCreateEmployeeButton (ActionListener createEmployeeButtonListener) {
        createEmployeeButton.addActionListener(createEmployeeButtonListener);
    }

    public void setViewAllEmployeesButton (ActionListener viewAllEmployeesButtonListener) {
        viewAllEmployeesButton.addActionListener(viewAllEmployeesButtonListener);
    }

    public void setDeleteEmployeeButton (ActionListener deleteEmployeeButtonListener) {
        deleteEmployeeButton.addActionListener(deleteEmployeeButtonListener);
    }

    public void setUpdateEmployeeButton (ActionListener updateEmployeeButtonListener) {
        updateEmployee.addActionListener(updateEmployeeButtonListener);
    }

    public void setGenerateActivityButton (ActionListener generateActivityButtonListener) {
        generateActivityButton.addActionListener(generateActivityButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

}
