package Model.DAO;

import Model.DTO.Type;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;

    public static ArrayList<Type>getTypeDetails(){
        ArrayList<Type>types=new ArrayList<>();
        int typeId;
        String type;
        int price;

        try{
            query="select * from mvc_movie.type";
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                typeId = rs.getInt(1);
                type = rs.getString(2);
                price = rs.getInt(3);


                types.add(new Type(typeId, type, price));
            }
        }catch(SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
        return types;

    }
    public static Type getTypeDetail(int id) {

        Type m = new Type();

        try{
            query = "select * from mvc_movie.TypeDetails where TypeID = '"+ id + "';";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()) {
                m.setTypeId(rs.getInt("TypeID"));
                m.setType(rs.getString("Type"));
                m.setPrice(rs.getInt("Price"));
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }

        return m;
    }

    public static void addNewType(Type td) throws SQLException {

        String query;

        try {
            query = "INSERT INTO mvc_movie.typedetails (TypeID, Type, Price) VALUES (?, ?, ?);";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, td.getTypeId());
            pst.setString(2, td.getType());
            pst.setInt(3, td.getPrice());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("=====================================");
                System.out.println("   Type Added Successfully!!!       ");
                System.out.println("=====================================");
            } else {
                System.out.println("Failed to add type.");
            }
            pst.close();
            con.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
    }

    public static void editType(Type td, int typeID){
        String query;

        try{
            query = "UPDATE mvc_movie.typedetails SET Type = '" + td.getType() + "', "
                    + "Price = '" + td.getPrice() + "' WHERE typeID = '" + typeID + "';";
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
    }

    public static boolean typeExists(int typeID){

        try{
            query = "select typeid from mvc_movie.typedetails where typeid = '" + typeID + "'";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }

        return false;
    }
}
