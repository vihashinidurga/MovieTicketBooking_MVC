package Model.DAO;

import Model.DTO.Seat;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeatDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;

    public static ArrayList<Seat> getSeats(){
        ArrayList<Seat>seats=new ArrayList<>();
        int seatId;
        char row;
        char col;
        int cinemaId;

        try{
            query="select * from mvc_movie.seat";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                seatId=rs.getInt(1);
                row=rs.getString(2).charAt(0);
                col=rs.getString(3).charAt(0);
                cinemaId=rs.getInt(4);

                seats.add(new Seat(seatId,row,col,cinemaId));
            }
        }catch (SQLException se){
            System.out.println("SQLEXCEPTON");
            se.printStackTrace();
        }

        return seats;
    }

     public static Seat getSeat(int id){
        Seat m = new Seat();

        try{
            query = "select * from mvc_movie.Seat where SeatID = '"+ id + "'";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if(rs.next()) {
                m.setSeatId(rs.getInt("SeatID"));
                m.setRow(rs.getString("Row").charAt(0));
                m.setColumn(rs.getString("Column").charAt(0));
                m.setCinemaId(rs.getInt("CinemaID"));
            }
        }catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
        return m;
    }

    public static ArrayList<Seat> getFreeSeats(int s){

        ArrayList<Seat> arr = new ArrayList<Seat>();

        int SeatID;
        char Row;
        char Column;
        int CinemaID;

        String schedID = Integer.toString(s);
        try{
            query = "Select s.* from mvc_movie.seat s, mvc_movie.schedule sc, mvc_movie.cinema c where sc.CinemaID = c.Screen_ID and s.CinemaID=c.Screen_ID and ";
            query+= "sc.ScheduleID= '" + schedID + "' and s.SeatID not in (Select r.SeatID from mvc_movie.reservation r where r.ScheduleID='" + schedID +"')";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                SeatID = rs.getInt(1);
                Row = rs.getString(2).charAt(0);
                Column = rs.getString(3).charAt(0);
                CinemaID = rs.getInt(4);

                arr.add(new Seat(SeatID, Row, Column, CinemaID));
            }
        }catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }

        return arr;
    }
public static ArrayList<Seat> getFreeSeats_byCinemaId(int cinemaid) {
    ArrayList<Seat> freeseatsforcinema = new ArrayList<>();
    String query = "select * from seat where seatid not in(select seatid from reservation) and cinemaid= ?";
    try {
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, cinemaid); // Set the cinemaId in the prepared statement

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Seat seat = new Seat();
            seat.setSeatId(rs.getInt("SeatId"));
            seat.setRow(rs.getString(2).charAt(0));
            seat.setColumn(rs.getString(3).charAt(0));
            seat.setCinemaId(cinemaid);

            freeseatsforcinema.add(seat); // Add seat to the list
        }
    } catch (SQLException e) {
        System.out.println("SQL Exception occurred");
        e.printStackTrace();
    }

    return freeseatsforcinema;
}

}
