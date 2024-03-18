package PatikaDev.Java102.JAVA.com.patikadev.View;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.Config;
import PatikaDev.Java102.JAVA.com.patikadev.Helper.Helper;
import PatikaDev.Java102.JAVA.com.patikadev.Model.Operator;
import PatikaDev.Java102.JAVA.com.patikadev.Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_userUName;
    private JTextField fld_userPass;
    private JButton btn_login;
    private JLabel lbl_patikaIcon;

    public LoginGUI() {
        Helper.setLayout();
        add(wrapper);
        setSize(400, 400);
        Helper.screenCenterPoint(1, this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userUName) || Helper.isFieldEmpty(fld_userPass)) {
                Helper.showMsg("empty field", null, this);
            } else {
                User u = User.getFetch(fld_userUName.getText(), fld_userPass.getText());
                if (u == null) {
                    Helper.showMsg("error", "The user cannot be found!", this);
                } else {
                    Helper.showMsg("done", "Welcome! " + u.getName(), this);
                    switch (u.getType()) {
                        case "operator":
                            OperatorGUI opGUI = new OperatorGUI((Operator) u);
                            break;
                        case "educator":
                            EducatorGUI edGUI = new EducatorGUI();
                            break;
                        case "student":
                            StudentGUI stGUI = new StudentGUI();
                            break;
                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
    }
}
