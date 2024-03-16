package PatikaDev.Java102.JAVA.com.patikadev.View;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.*;
import PatikaDev.Java102.JAVA.com.patikadev.Model.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private JTextField fld_shUserName;
    private JTextField fld_shUserUName;
    private JComboBox cmb_shUserType;
    private JButton btn_userSearch;
    private JPanel sh_user;
    private JPanel pnl_patikaList;
    private JScrollPane scrl_patikaList;
    private JTable tbl_patikaList;
    private JPanel pnl_patikaAdd;
    private JTextField fld_patikaName;
    private JButton btn_patikaAdd;
    private final DefaultTableModel mdl_userList;
    private final Object[] row_userList;
    private DefaultTableModel mdl_patikaList;
    private final Object[] row_patikaList;
    private JPopupMenu patikaMenu;

    public OperatorGUI(Operator operator) {
        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        setVisible(true);

        lbl_welcome.setText("Welcome : " + operator.getName());

        // Model user list
        mdl_userList = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column){
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_userList = {"ID", "Name Surname", "User Name", "Password", "User Type"};
        mdl_userList.setColumnIdentifiers(col_userList);
        row_userList = new Object[col_userList.length];
        loadUserModel();
        tbl_userList.setModel(mdl_userList);
        tbl_userList.getTableHeader().setReorderingAllowed(false);
        // ## Model user list

        // Model patika list
        patikaMenu = new JPopupMenu();                                      // Patika Popup Menu
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);                                         // ## Patika Popup Menu

        updateMenu.addActionListener(e -> {
            int selectID = Integer.parseInt(tbl_patikaList.getValueAt(tbl_patikaList.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(selectID));
        });

        mdl_patikaList = new DefaultTableModel();
        Object[] col_patikaList = {"ID", "Patika Name"};
        mdl_patikaList.setColumnIdentifiers(col_patikaList);
        row_patikaList = new Object[col_patikaList.length];
        loadPatikaModel();

        tbl_patikaList.setModel(mdl_patikaList);
        tbl_patikaList.setComponentPopupMenu(patikaMenu);
        tbl_patikaList.getTableHeader().setReorderingAllowed(false);
        tbl_patikaList.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patikaList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patikaList.rowAtPoint(point);
                tbl_patikaList.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        // ## Model patika list

        // Adding table list into the model
        tbl_userList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selectUserID = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString();
                fld_userID.setText(selectUserID);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        // Table cells Listener
        tbl_userList.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString());
                String name = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 1).toString();
                String userName = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 2).toString();
                String userPass = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 3).toString();
                String userType = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 4).toString();

                if (User.update(user_id, name, userName, userPass, userType)) {
                    Helper.showMsg("done", null);
                }

                loadUserModel();
            }
        });

        // Add button Listener
        btn_userAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userName) ||
                    Helper.isFieldEmpty(fld_userUName) ||
                    Helper.isFieldEmpty(fld_userPassword)) {
                Helper.showMsg("empty field", null);
            } else {
                String name = fld_userName.getText();
                String uname = fld_userUName.getText();
                String pass = fld_userPassword.getText();
                String type = cmb_userType.getSelectedItem().toString();
                if (User.add(name, uname, pass, type)) {
                    Helper.showMsg("done", null);
                    loadUserModel();
                    fld_userName.setText(null);
                    fld_userUName.setText(null);
                    fld_userPassword.setText(null);
                }
            }
        });

        // Delete button Listener
        btn_userDelete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userID)) {
                Helper.showMsg("empty field", null);
            } else {
                int userID = Integer.parseInt(fld_userID.getText());
                if (User.delete(userID)) {
                    Helper.showMsg("done", null);
                    loadUserModel();
                } else {
                    Helper.showMsg("error", "Delete operation is unsuccessful");
                }
            }
        });

        // Search button Listener
        btn_userSearch.addActionListener(e -> {
            String name = fld_shUserName.getText();
            String uname = fld_shUserUName.getText();
            String type = cmb_shUserType.getSelectedItem().toString();
            String query = User.searchQuery(name, uname, type);
            loadUserModel(User.searchUserList(query));
        });

        btn_logout.addActionListener(e -> {
            dispose();
        });

        btn_patikaAdd.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patikaName)) {
                Helper.showMsg("fill", null);
            } else {
                if (Patika.add(fld_patikaName.getText())) {
                    Helper.showMsg("done", null);
                    loadPatikaModel();
                    fld_patikaName.setText(null);
                } else {
                    Helper.showMsg("error", null);
                }
            }
        });
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patikaList.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj: Patika.getList()) {
            i = 0;
            row_patikaList[i++] = obj.getId();
            row_patikaList[i++] = obj.getName();
            mdl_patikaList.addRow(row_patikaList);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User obj: User.getUserList()) {
            i = 0;
            row_userList[i++] = obj.getId();
            row_userList[i++] = obj.getName();
            row_userList[i++] = obj.getUname();
            row_userList[i++] = obj.getPass();
            row_userList[i++] = obj.getType();
            mdl_userList.addRow(row_userList);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);
        for (User obj: list) {
            int i = 0;
            row_userList[i++] = obj.getId();
            row_userList[i++] = obj.getName();
            row_userList[i++] = obj.getUname();
            row_userList[i++] = obj.getPass();
            row_userList[i++] = obj.getType();
            mdl_userList.addRow(row_userList);
        }
    }

    public static void main(String[] args) {
        Operator op = new Operator();
        op.setId(1);
        op.setName("Korhan Çakmak");
        op.setPass("123");
        op.setType("operator");
        op.setUname("kcakmak");

        OperatorGUI opGUI = new OperatorGUI(op);
    }
}
