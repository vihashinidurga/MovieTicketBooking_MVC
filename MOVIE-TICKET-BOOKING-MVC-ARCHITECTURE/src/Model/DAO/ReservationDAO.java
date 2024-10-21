package Model.DAO;
///correvtion in insert statement
import Model.DTO.Reservation;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ReservationDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;

    public static ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reserve = new ArrayList<Reservation>();
        int scheduleId;
        int seatId;
        String email;
        int userId;
        int nSeats;
        try{
            query = "select * from mvc_movie.reservation";

            Connection con= DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()){
                scheduleId=rs.getInt(1);
                seatId=rs.getInt(2);
                email=rs.getString(3);
                userId=rs.getInt(4);
                nSeats=rs.getInt(5);
                reserve.add(new Reservation(scheduleId,seatId,email,userId,nSeats));
            }
        }catch (SQLException e){
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }

        return reserve;
    }
    public static Reservation getReservaton(int id){
        Reservation res=new Reservation();
        query="select * from mvc_movie.reservation where reservationId = '" +id+"'";

        try{
            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                res.setScheduleID(rs.getInt(1));
                res.setSeatID(rs.getInt(2));
                res.setEmail(rs.getString(3));
                res.setUserID(rs.getInt(4));
            }
        }catch (SQLException se) {
            System.out.println("SQL EXEPTION");
            se.printStackTrace();
        }
        return res;
    }
    public static boolean isValidEmail(String email) {
        String query = "SELECT COUNT(*) FROM `mvc_movie`.`account` WHERE `Email` = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addReservation(Reservation reservation,int cd) {
        String query = "INSERT INTO mvc_movie.reservation (ScheduleID, SeatID, Email, UserID, NumberOfSeats) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, reservation.getScheduleID());
            pst.setInt(2, reservation.getSeatID());
            pst.setString(3, reservation.getEmail());
            pst.setInt(4, reservation.getUserID());
            pst.setInt(5, reservation.getNumberOfSeats());

            int count = pst.executeUpdate();
            System.out.println(count + " rows were added/updated");
            addHistory(reservation,cd);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addHistory(Reservation reservation,int cd) {
        String historyQuery = "INSERT INTO mvc_movie.history (RecordID, ScheduleID, SeatID, Email, UserID, NumberOfSeats, Price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(historyQuery)) {

            String recordId = String.valueOf(1000+reservation.getUserID()+reservation.getSeatID());
            pst.setString(1, recordId);
            pst.setInt(2, reservation.getScheduleID());
            pst.setInt(3, reservation.getSeatID());
            pst.setString(4, reservation.getEmail());
            pst.setInt(5, reservation.getUserID());
            pst.setInt(6, reservation.getNumberOfSeats());
            pst.setDouble(7, calculatePrice(cd,reservation.getNumberOfSeats()));
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static int calculatePrice(int cid, int nseats) {
        int pricePerSeat = 0;
        String query = "SELECT price FROM typedetails WHERE typeid = (SELECT typeid FROM cinema WHERE screen_id = ?)";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, cid);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pricePerSeat = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
            e.printStackTrace();
        }
        return pricePerSeat * nseats;
    }
}
