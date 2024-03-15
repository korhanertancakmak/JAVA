package PatikaDev.Java102.JAVA.com.patikadev.View;


import PatikaDev.Java102.JAVA.com.patikadev.Helper.*;
import PatikaDev.Java102.JAVA.com.patikadev.Model.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_userList;
    private JScrollPane scrl_userList;
    private JTable tbl_userList;
    private JPanel pnl_userForm;
    private JTextField fld_userUName;
    private JTextField fld_userName;
    private JTextField fld_userPassword;
    private JComboBox cmb_userType;
    private JButton btn_userAdd;
    private JTextField fld_userID;
    private JButton btn_userDelete;
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
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_userList = {"ID", "Name Surname", "User Name", "Password", "User Type"};
        mdl_user_list.setColumnIdentifiers(col_userList);
        row_userList = new Object[col_userList.length];

        loadUserModel();
        tbl_userList.setModel(mdl_user_list);
        tbl_userList.getTableHeader().setReorderingAllowed(false);

        tbl_userList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectUserID = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString();
                fld_userID.setText(selectUserID);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        btn_userAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userName) ||
                    Helper.isFieldEmpty(fld_userUName) ||
                    Helper.isFieldEmpty(fld_userPassword)) {
                Helper.showMsg("fill");
            } else {
                String name = fld_userName.getText();
                String uname = fld_userUName.getText();
                String pass = fld_userPassword.getText();
                String type = cmb_userType.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    fld_userName.setText(null);
                    fld_userUName.setText(null);
                    fld_userPassword.setText(null);
                }
            }
        });
        btn_userDelete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userID)) {
                Helper.showMsg("fill");
            } else {
                int userID = Integer.parseInt(fld_userID.getText());
                if (User.delete(userID)) {
                    Helper.showMsg("done");
                    loadUserModel();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);
        for (User obj: User.getUserList()) {
            int i = 0;
            row_userList[i++] = obj.getId();
            row_userList[i++] = obj.getName();
            row_userList[i++] = obj.getUname();
            row_userList[i++] = obj.getPass();
            row_userList[i++] = obj.getType();
            mdl_user_list.addRow(row_userList);
        }
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
