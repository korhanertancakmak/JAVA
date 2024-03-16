package PatikaDev.Java102.JAVA.com.patikadev.View;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.Config;
import PatikaDev.Java102.JAVA.com.patikadev.Helper.Helper;
import PatikaDev.Java102.JAVA.com.patikadev.Model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_patikaName;
    private JButton btn_update;
    private Patika patika; // This is the selected patika object in here

    public UpdatePatikaGUI(Patika patika) {
        this.patika = patika;
        add(wrapper);
        setSize(300, 150);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_patikaName.setText(patika.getName());

        btn_update.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patikaName)) {
                Helper.showMsg("fill", null);
            } else {
                if (Patika.update(patika.getId(), fld_patikaName.getText())) {
                    Helper.showMsg("done", null);
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Patika p = new Patika(1, "Frontend");
        UpdatePatikaGUI up = new UpdatePatikaGUI(p);
    }
}