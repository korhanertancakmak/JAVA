package PatikaDev.Java102.JAVA.com.patikadev.View;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.Config;
import PatikaDev.Java102.JAVA.com.patikadev.Helper.Helper;

import javax.swing.*;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;

    public EducatorGUI(){
        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        Helper.screenCenterPoint(1, this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
    }
}
