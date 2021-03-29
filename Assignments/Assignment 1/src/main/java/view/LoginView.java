package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginView extends JFrame {

    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btnLogin;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(usernameLabel);
        add(tfUsername);
        add(passwordLabel);
        add(tfPassword);
        add(btnLogin);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        tfUsername = new JTextField();
        tfPassword = new JTextField();
        btnLogin = new JButton("Login");

        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

}
