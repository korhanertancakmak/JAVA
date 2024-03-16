package PatikaDev.Java102.JAVA.com.patikadev.Model;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.DBConnector;
import PatikaDev.Java102.JAVA.com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patika {
    private int id;
    private String name;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Patika> getList() {
        ArrayList<Patika> patikaList = new ArrayList<>();
        String query = "SELECT * FROM patika";
        Patika obj;
        try (Statement st = DBConnector.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                obj = new Patika(rs.getInt("id"), rs.getString("name"));
                patikaList.add(obj);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patikaList;
    }

    public static boolean add(String name) {
        String query = "INSERT INTO patika (name) VALUES (?)";
        /*User findUser = User.getFetch(uname);

        if (findUser != null) {
            Helper.showMsg("error", "This user is already added before. Please enter different user name!");
            return false;
        }*/
        try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
            pr.setString(1, name);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error", "Something goes wrong...");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
