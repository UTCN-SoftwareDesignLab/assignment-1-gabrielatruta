package view;

import model.dto.AccountDTO;
import model.dto.ClientDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel ICNLabel;
    private JLabel addressLabel;
    private JLabel mailLabel;
    private JLabel phoneLabel;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfICN;
    private JTextField tfAddress;
    private JTextField tfMail;
    private JTextField tfPhone;
    private JButton createClientButton;
    private JButton viewClientsButton;
    private JButton updateClientButton;

    private JLabel IBANLabel;
    private JLabel typeLabel;
    private JLabel amountOfMoneyLabel;
    private JTextField tfIBAN;
    private JTextField tfType;
    private JTextField tfMoney;
    private JButton createAccountButton;
    private JButton viewAccountsButton;
    private JButton deleteAccountButton;
    private JButton updateAccountButton;
    private JButton transferMoneyButton;

    private JButton logoutButton;
    private Long userConnected = (long) -1;

    public EmployeeView() throws HeadlessException {

        setSize(700, 700);
       // setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        initializeFields();
        setLayout(null);
        add(nameLabel);
        add(tfName);
        add(surnameLabel);
        add(tfSurname);
        add(ICNLabel);
        add(tfICN);
        add(addressLabel);
        add(tfAddress);
        add(mailLabel);
        add(tfMail);
        add(phoneLabel);
        add(tfPhone);
        add(createClientButton);
        add(viewClientsButton);
        add(updateClientButton);
        add(IBANLabel);
        add(tfIBAN);
        add(typeLabel);
        add(tfType);
        add(amountOfMoneyLabel);
        add(tfMoney);
        add(createAccountButton);
        add(viewAccountsButton);
        add(deleteAccountButton);
        add(updateAccountButton);
        add(transferMoneyButton);
        add(logoutButton);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(15, 15, 80, 20);
        tfName = new JTextField();
        tfName.setBounds(55, 15, 80, 20);

        surnameLabel = new JLabel("Surname: ");
        surnameLabel.setBounds(150, 15, 100, 20);
        tfSurname = new JTextField();
        tfSurname.setBounds(200, 15, 80, 20);

        ICNLabel = new JLabel("ICN: ");
        ICNLabel.setBounds(350, 15, 80, 20);
        tfICN = new JTextField();
        tfICN.setBounds(380, 15, 80, 20);

        addressLabel = new JLabel("Address: ");
        addressLabel.setBounds(15, 100, 100, 20);
        tfAddress = new JTextField();
        tfAddress.setBounds(75, 100, 100, 20);

        mailLabel = new JLabel("Mail: ");
        mailLabel.setBounds(175, 100, 80, 20);
        tfMail = new JTextField();
        tfMail.setBounds(215, 100, 80, 20);

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setBounds(350, 100, 100, 20);
        tfPhone = new JTextField();
        tfPhone.setBounds(400, 100, 100, 20);

        IBANLabel = new JLabel("IBAN: ");
        IBANLabel.setBounds(15, 350, 80, 20);
        tfIBAN = new JTextField();
        tfIBAN.setBounds(55, 350, 80, 20);

        typeLabel = new JLabel("Type: ");
        typeLabel.setBounds(15, 380, 80, 20);
        tfType = new JTextField();
        tfType.setBounds(55, 380, 80, 20);

        amountOfMoneyLabel = new JLabel("Money: ");
        amountOfMoneyLabel.setBounds(15, 410, 80, 20);
        tfMoney = new JTextField();
        tfMoney.setBounds(65, 410, 80, 20);

        createClientButton = new JButton("Create");
        createClientButton.setBounds(15, 200, 80, 40);

        viewClientsButton = new JButton("View");
        viewClientsButton.setBounds(100, 200, 80, 40);

        updateClientButton = new JButton("Update");
        updateClientButton.setBounds(185, 200, 80, 40);

        createAccountButton = new JButton("Create");
        createAccountButton.setBounds(15, 450, 80, 40);

        viewAccountsButton = new JButton("View");
        viewAccountsButton.setBounds(100, 450, 80, 40);

        deleteAccountButton = new JButton("Delete");
        deleteAccountButton.setBounds(185, 450, 80, 40);

        updateAccountButton = new JButton("Update");
        updateAccountButton.setBounds(270, 450, 80, 40);

        transferMoneyButton = new JButton("Transfer");
        transferMoneyButton.setBounds(355, 450, 100, 40);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(600, 600, 80, 40);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ClientDTO getClientDTO(){
        return new ClientDTO(tfName.getText(), tfSurname.getText(), tfAddress.getText(), tfICN.getText(), tfMail.getText(), tfPhone.getText(), tfMail.getText());
    }

    public AccountDTO getAccountDTO(){
        return new AccountDTO(tfIBAN.getText(), tfType.getText(), Long.parseLong(tfMoney.getText()));
    }


    public JTextField getTfName() {
        return tfName;
    }

    public void setTfName(JTextField tfName) {
        this.tfName = tfName;
    }

    public JTextField getTfSurname() {
        return tfSurname;
    }

    public void setTfSurname(JTextField tfSurname) {
        this.tfSurname = tfSurname;
    }

    public JTextField getTfICN() {
        return tfICN;
    }

    public void setTfICN(JTextField tfICN) {
        this.tfICN = tfICN;
    }

    public JTextField getTfAddress() {
        return tfAddress;
    }

    public void setTfAddress(JTextField tfAddress) {
        this.tfAddress = tfAddress;
    }

    public JTextField getTfMail() {
        return tfMail;
    }

    public void setTfMail(JTextField tfMail) {
        this.tfMail = tfMail;
    }

    public JTextField getTfPhone() {
        return tfPhone;
    }

    public void setTfPhone(JTextField tfPhone) {
        this.tfPhone = tfPhone;
    }

    public JTextField getTfIBAN() {
        return tfIBAN;
    }

    public void setTfIBAN(JTextField tfIBAN) {
        this.tfIBAN = tfIBAN;
    }

    public JTextField getTfType() {
        return tfType;
    }

    public void setTfType(JTextField tfType) {
        this.tfType = tfType;
    }

    public JTextField getTfMoney() {
        return tfMoney;
    }

    public void setTfMoney(JTextField tfMoney) {
        this.tfMoney = tfMoney;
    }

    public Long getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(Long userConnected) {
        this.userConnected = userConnected;
    }

    public void setLogoutButtonListener (ActionListener logoutButtonListener){
        logoutButton.addActionListener(logoutButtonListener);
    }

    public void setCreateClientButtonListener (ActionListener actionListener) {
        createClientButton.addActionListener(actionListener);
    }

    public void setViewClientsButtonListener (ActionListener actionListener){
        viewClientsButton.addActionListener(actionListener);
    }

    public void setUpdateClientButtonListener (ActionListener actionListener){
        updateClientButton.addActionListener(actionListener);
    }

    public void setViewAccountsButtonListener (ActionListener actionListener){
        viewAccountsButton.addActionListener(actionListener);
    }

    public void setCreateAccountButtonListener (ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }

    public void setDeleteAccountButtonListener (ActionListener actionListener){
        deleteAccountButton.addActionListener(actionListener);
    }

    public void setUpdateAccountButtonListener (ActionListener actionListener){
        updateAccountButton.addActionListener(actionListener);
    }

    public void setTransferMoneyButtonListener (ActionListener actionListener){
        transferMoneyButton.addActionListener(actionListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }
}
