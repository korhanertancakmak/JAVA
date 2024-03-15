package PatikaDev.Java102.JAVA.com.patikadev.View;


import PatikaDev.Java102.JAVA.com.patikadev.Helper.*;
import PatikaDev.Java102.JAVA.com.patikadev.Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OperatorGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_userList;
    private JScrollPane scrl_userList;
    private JTable tbl_userList;
    private DefaultTableModel mdl_user_list;
    private Object[] row_userList;

    private final Operator operator;

    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome : " + operator.getName());

        // ModelUserList
        mdl_user_list = new DefaultTableModel();
        Object[] col_userList = {"ID", "Name Surname", "User Name", "Password", "User Type"};
        mdl_user_list.setColumnIdentifiers(col_userList);

        for (User obj: User.getUserList()) {
            Object[] row = new Object[col_userList.length];
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUname();
            row[3] = obj.getPass();
            row[4] = obj.getType();
            mdl_user_list.addRow(row);
        }
        tbl_userList.setModel(mdl_user_list);
        tbl_userList.getTableHeader().setReorderingAllowed(false);
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Korhan Çakmak");
        op.setPass("123");
        op.setType("operator");
        op.setUname("kcakmak");



        OperatorGUI opGUI = new OperatorGUI(op);
    }
}
