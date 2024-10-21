package Model.DAO;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import Model.DTO.Schedule;

public class ScheduleDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie ";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;
    public static ArrayList<Schedule> getSchedule(){
        ArrayList<Schedule> scd = new ArrayList<Schedule>();

        int ScheduleID;
        int CinemaID;
        int MovieID;
        Time time;
        Date date;
        LocalDate ld;
        LocalTime lt;

        try{
            query = "select * from mvc_movie.schedule";
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                ScheduleID=rs.getInt(1);
                CinemaID=rs.getInt(2);
                MovieID=rs.getInt(3);
                time=rs.getTime("Time");
                date=rs.getDate("Date");

                ld=date.toLocalDate();
                lt=time.toLocalTime();

                Schedule schedule = new Schedule(ScheduleID,CinemaID,MovieID,ld,lt);
                scd.add(schedule);

            }

        }catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
        return scd;
    }
public static ArrayList<Schedule> getScheduleId(int movieId) {
    ArrayList<Schedule> schedules = new ArrayList<>();
    String query;

    try {
        query = "SELECT scheduleid, cinemaid, movieid, Date, Time FROM schedule WHERE movieid = '" + movieId + "'";
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getInt("scheduleid"));
            schedule.setCinemaId(rs.getInt("cinemaid"));
            schedule.setMovieId(rs.getInt("movieid"));
            schedule.setDate(rs.getDate("Date").toLocalDate());
            schedule.setTime(rs.getTime("Time").toLocalTime());

            schedules.add(schedule);
        }
    } catch (SQLException e) {
        System.out.println("SQL EXCEPTION");
        e.printStackTrace();
    }

    return schedules;
}

    public static ArrayList<Schedule> getLatestSchedule(){
        ArrayList<Schedule> scd = new ArrayList<Schedule>();

        int ScheduleID;
        int CinemaID;
        int MovieID;
        Time time;
        Date date;
        LocalDate ld;
        LocalTime lt;

        try{
            query = "select * from schedule s where concat(s.date, ' ', s.time)>concat(curdate(), ' ', curtime());";

            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                ScheduleID = rs.getInt(1);
                CinemaID = rs.getInt(2);
                MovieID = rs.getInt(3);
                time = rs.getTime(4);
                lt = time.toLocalTime();

                date = rs.getDate(5);
                ld = date.toLocalDate();

                scd.add(new Schedule(ScheduleID, CinemaID, MovieID, ld, lt));
            }
        }catch (SQLException e){
            System.out.println("Exception");
            e.printStackTrace();
        }
        return scd;
    }
    public static void addNewSchedule(Schedule s){
         String query;

        Time time = Time.valueOf(s.getTime());
        Date date = Date.valueOf(s.getDate());

        try{
            query = "INSERT INTO mvc_movie.schedule (ScheduleID, CinemaID, MovieID, Time, Date) VALUES (?, ?, ?, ?, ?);";

            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement pst=con.prepareStatement(query);

            pst.setInt(1, s.getScheduleId());
            pst.setInt(2, s.getCinemaId());
            pst.setInt(3, s.getMovieId());
            pst.setTime(4, time);
            pst.setDate(5, date);

            int rs= pst.executeUpdate();

            if(rs>0){
                System.out.println("=====================================");
                System.out.println("   Schedule Added Successfully!!!       ");
                System.out.println("=====================================");
            }else{
                System.out.println("Failed to add Schedule");
            }

        }catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
    }

    public static void editSchedule(Schedule s,int oldSID){
        String query;

        Time time = Time.valueOf(s.getTime());
        Date date = Date.valueOf(s.getDate());

        try{
            query = "UPDATE mvc_movie.schedule SET CinemaID = '" + s.getCinemaId()
                    + "', MovieID = '" + s.getMovieId() + "', " + "Time = '" + time + "', Date = '" + date + "' WHERE ScheduleID = '"+ oldSID +"';";
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            int rs = st.executeUpdate(query);
        }catch (SQLException e){
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
    public static boolean scheduleExists(int schedID){

        try{
            query = "select scheduleid from mvc_movie.schedule where scheduleid = '" + schedID + "'";
            Connection con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                return true;
            }
        }catch (SQLException se) {
            System.out.println("SQL Exception occurred");
            se.printStackTrace();
        }
        return false;
    }

}
