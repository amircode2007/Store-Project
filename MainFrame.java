package ui;

import util.SessionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Shopping Mall");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton adminBtn = new JButton("Admin Panel");
        JButton customerBtn = new JButton("Customer Panel");
        JButton logoutBtn = new JButton("Logout");

        adminBtn.addActionListener(e -> {
            setContentPane(new AdminPanel());
            revalidate();
        });

        customerBtn.addActionListener(e -> {
            setContentPane(new CustomerPanel(this));
            revalidate();
        });

        logoutBtn.addActionListener(e ->
                SessionManager.logout(this));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(adminBtn);
        panel.add(customerBtn);
        panel.add(logoutBtn);

        add(panel);
    }
}
