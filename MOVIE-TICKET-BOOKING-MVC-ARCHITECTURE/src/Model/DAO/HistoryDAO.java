package Model.DAO;

import Model.DTO.History;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;
    public static ArrayList<History>getHistory(){
        ArrayList<History>hist=new ArrayList<History>();
        String RecordID;
        int scheduleID;
        int seatID;
        String email;
        int userID;
        int price;

        try {
            query="select * from mvc_movie.history";
            Connection con= DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()) {
                RecordID = rs.getString(1);
                scheduleID = rs.getInt(2);
                seatID = rs.getInt(3);
                email = rs.getString(4);
                userID = rs.getInt(5);
                price = rs.getInt(6);

                hist.add(new History(RecordID, scheduleID, seatID, email, userID, price));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e){
            System.err.println("Exception "+e.getMessage());
            return null;
        }
        return hist;
    }
    public static int getRevenue(){
        int revenue=0;
        try{
            query="select sum(Price) from mvc_movie.history";
            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            if (rs.next()){
                revenue=rs.getInt(1);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException e){
            System.err.println("Exception occured "+e.getMessage());
            revenue=-1;
        }
        return revenue;
    }
}
