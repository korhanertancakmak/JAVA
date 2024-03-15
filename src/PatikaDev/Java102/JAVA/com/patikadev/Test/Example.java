package com.patikadev.Test;

import javax.swing.*;
import java.awt.*;

public class Example extends JFrame{
    private JPanel wrapper;
    private JPanel wTop;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;

    public Example() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        add(wrapper);
        setSize(600,400);
        setTitle("Application Name");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if(fld_username.getText().isEmpty() || fld_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Please fill all the fields!", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println(fld_username.getText());
            }
        });
    }
}
