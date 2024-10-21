package Controller;

import Model.DAO.*;
import Model.DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {
    public static int getRevenue() {
        int revenue=HistoryDAO.getRevenue();
        if(revenue==-1)
            System.out.println("Unable to fetch revenue");
        return revenue;
    }
    public static Cinema getCinema(int id){
        return CinemaDAO.getCinema(id);
    }
    public static ArrayList<Cinema> getCinemas() {
        return CinemaDAO.getCinemas();
    }
    public static void addNewMovie(Movie movie) {
        MovieDAO.addNewMovie(movie);
    }
    public static void editMovie(Movie movie,int oldMovId){
        MovieDAO.editMovie(movie,oldMovId);
    }
    public static Movie getMovie(int id){
        return MovieDAO.getMovie(id);
    }
    public static boolean movieExists(int movieId){
        return MovieDAO.movieExists(movieId);
    }
    public static ArrayList<Reservation>getReservations() {
        return ReservationDAO.getReservations();
    }
    public static ArrayList<Schedule> getSchedule() {
        return ScheduleDAO.getSchedule();
    }
    public static void addNewSchedule(Schedule s){
        ScheduleDAO.addNewSchedule(s);
    }
    public static void editSchedule(Schedule s, int oldSID){
        ScheduleDAO.editSchedule(s,oldSID);
    }
    public static boolean scheduleExists(int schedID){
        return ScheduleDAO.scheduleExists(schedID);
    }
    public static ArrayList<Seat> getSeats() {
        return SeatDAO.getSeats();
    }
    public static ArrayList<Seat> getFreeSeats(int s){
        return SeatDAO.getFreeSeats(s);
    }
    public static void addNewType(Type td) throws SQLException {
        TypeDAO.addNewType(td);
    }
    public static void editType(Type td, int typeID)  {
        TypeDAO.editType(td,typeID);
    }
    public static boolean typeExists(int typeID)  {
        return TypeDAO.typeExists(typeID);
    }

}
