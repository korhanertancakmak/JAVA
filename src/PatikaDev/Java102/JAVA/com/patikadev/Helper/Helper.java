package PatikaDev.Java102.JAVA.com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    private static String msg;

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("CDE/Motif".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int screenCenterPoint(String axis, Dimension size) {
        int point = 0;
        switch (axis) {
            case "x" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str, String message) {
        msg = message;
        String title;
        switch (str) {
            case "empty field":
                if (msg == null) {
                    msg = "Please fill all fields!";
                }
                title = "Error!";
                optionPageEng(false);
                break;
            case "done":
                if (msg == null) {
                    msg = "Operation is successful";
                }
                title = "Result";
                optionPageEng(true);
                break;
            case "error":
                if (msg == null) {
                    msg = "Something goes wrong...";
                }
                title = "Error";
                break;
            default:
                msg = str;
                title = "Message!";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;
        switch (str) {
            case "sure":
                msg = "Are you sure for this operation?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Last Decision?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageEng(boolean isDone) {
        if (isDone) {
            UIManager.put("OptionPane.okButtonText", "DONE");
        } else {
            UIManager.put("OptionPane.okButtonText", "TRY AGAIN");
        }
    }
}
