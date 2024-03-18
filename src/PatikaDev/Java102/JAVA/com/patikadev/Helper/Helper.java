package PatikaDev.Java102.JAVA.com.patikadev.Helper;

import PatikaDev.Java102.JAVA.com.patikadev.View.OperatorGUI;

import javax.swing.*;
import java.awt.*;

public class Helper {
    private static String msg;

    public static void setLayout() {
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
    }

    public static void screenCenterPoint(int screenID, Frame frame) {
        GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

        if (screenID > gd.length && gd.length > 0) {
            screenID = 0;
        } else if (gd.length == 0){
            throw new RuntimeException( "No Screens Found" );
        }

        int width = gd[screenID].getDefaultConfiguration().getBounds().width;
        int height = gd[screenID].getDefaultConfiguration().getBounds().height;
        frame.setLocation(
                ((width / 2) - (frame.getSize().width / 2)) + gd[screenID].getDefaultConfiguration().getBounds().x,
                ((height / 2) - (frame.getSize().height / 2)) + gd[screenID].getDefaultConfiguration().getBounds().y
        );
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str, String message, JFrame frame) {
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
        JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str, JFrame frame) {
        String msg;
        switch (str) {
            case "sure":
                msg = "Are you sure for this operation?";
                break;
            default:
                msg = str;
        }

        return JOptionPane.showConfirmDialog(frame, msg, "Last Decision?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageEng(boolean isDone) {
        if (isDone) {
            UIManager.put("OptionPane.okButtonText", "DONE");
        } else {
            UIManager.put("OptionPane.okButtonText", "TRY AGAIN");
        }
    }
}
