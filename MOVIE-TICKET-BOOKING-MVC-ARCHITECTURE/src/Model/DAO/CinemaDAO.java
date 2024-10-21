package Model.DAO;

import Model.DTO.Cinema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;

    public static ArrayList<Cinema> getCinemas(){
        ArrayList<Cinema> arr = new ArrayList<Cinema>();
        int screenId;
        String screenName;
        int noOfSeats;
        int typeId;

        try{
            query = "select * from mvc_movie.cinema";

            Connection con = DriverManager.getConnection(url, username,password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                screenId = rs.getInt(1);
                screenName = rs.getString(2);
                noOfSeats = rs.getInt(3);
                typeId = rs.getInt(4);

                arr.add(new Cinema(screenId, screenName, noOfSeats, typeId));
            }
        } catch (SQLException e){
            System.out.println("Exception "+e.getMessage());
            return null;
        }
        return arr;
    }

    public static Cinema getCinema(int id){

        Cinema cinema=null;
        int screenId;
        String screenName;
        int noOfSeats;
        int typeId;

        try{
            query = "select * from mvc_movie.Cinema where Screen_ID = '" + id + "';";
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                 screenId=rs.getInt(1);
                 screenName=rs.getString(2);
                 noOfSeats=rs.getInt(3);
                 typeId=rs.getInt(4);
                 cinema=new Cinema(screenId,screenName,noOfSeats,typeId);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException se) {
            System.out.println("SQL EXCEPTION"+se.getMessage());
        }
        return cinema;
    }
    public static int cinemaId(int scheduleId) {
        int cid = -1;
        String query = "SELECT screen_id FROM cinema WHERE screen_id = (SELECT cinemaid FROM schedule WHERE scheduleid = ?)";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, scheduleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cid = rs.getInt("screen_id");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
            e.printStackTrace();
        }
        return cid;
    }

}
