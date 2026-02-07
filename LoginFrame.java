package ui;


import data.UserStore;
import user.AuthException;


import javax.swing.*;


public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login / Sign Up");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);


        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");


        loginBtn.addActionListener(e -> {
            try {
                UserStore.login(userField.getText(), new String(passField.getPassword()));
                new MainFrame().setVisible(true);
                dispose();
            } catch (AuthException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });


        signupBtn.addActionListener(e -> {
            try {
                UserStore.signUp(userField.getText(), new String(passField.getPassword()));
                JOptionPane.showMessageDialog(this, "Account created successfully");
            } catch (AuthException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });


        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(loginBtn);
        panel.add(signupBtn);


        add(panel);
    }
}
