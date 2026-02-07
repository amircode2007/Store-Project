package util;

import ui.LoginFrame;

import javax.swing.*;

public class SessionManager {

    public static void logout(JFrame currentFrame) {
        currentFrame.dispose();
        new LoginFrame().setVisible(true);
    }
}
