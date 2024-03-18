package PatikaDev.Java102.JAVA.com.patikadev.Model;

import PatikaDev.Java102.JAVA.com.patikadev.Helper.DBConnector;
import PatikaDev.Java102.JAVA.com.patikadev.Helper.Helper;
import com.sun.jdi.ArrayReference;

import javax.swing.*;
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

    public static ArrayList<Patika> getPatikaList() {
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

    public static boolean add(String name, JFrame frame) {
        String query = "INSERT INTO patika (name) VALUES (?)";
        try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
            pr.setString(1, name);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error", "Something goes wrong...", frame);
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean update(int id, String name) {
        String query = "UPDATE patika SET name = ? WHERE id = ?";
        try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
            pr.setString(1, name);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static Patika getFetch(int id) {
        Patika obj = null;
        String query = "SELECT * FROM patika WHERE id = ?";

        try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Patika(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM patika WHERE id = ?";
        ArrayList<Course> coruseList = Course.getCourseList();
        for (Course obj : coruseList) {
            if (obj.getPatika().getId() == id) {
                Course.delete(obj.getId());
            }
        }
        try (PreparedStatement pr = DBConnector.getInstance().prepareStatement(query)) {
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
