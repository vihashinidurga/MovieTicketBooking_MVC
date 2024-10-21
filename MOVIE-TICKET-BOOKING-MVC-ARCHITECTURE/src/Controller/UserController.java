package Controller;

import Model.DAO.*;
import Model.DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    public static void addAccount(Account acc){
        AccountDAO.addNewAccount(acc);
    }
    public static ArrayList<Cinema> getCinemas(){
        return CinemaDAO.getCinemas();
    }
    public static int cinemaId(int scheduleid){return CinemaDAO.cinemaId(scheduleid);}
    public static ArrayList<History>getHistory(){
        return HistoryDAO.getHistory();
    }
    public static ArrayList<Movie> getLatestMovies(){
        return MovieDAO.getLatestMovies();
    }
    public static Movie getMovie(int id){
        return MovieDAO.getMovie(id);
    }
    public static ArrayList<Movie> getMovies(){
        return MovieDAO.getMovies();
    }
    public static boolean movieExists(int movieId){
        return MovieDAO.movieExists(movieId);
    }
    public static void addReservation(Reservation reservation,int cd){ReservationDAO.addReservation(reservation,cd);}
    public static int calculatePrice(int y,int u){return ReservationDAO.calculatePrice(y,u);}
    public static ArrayList<Schedule> getLatestSchedule()  {
        return ScheduleDAO.getLatestSchedule();
    }
    public static ArrayList<Schedule> getSchedule() {
        return ScheduleDAO.getSchedule();
    }
    public static boolean scheduleExists(int schedID){
        return ScheduleDAO.scheduleExists(schedID);
    }
    public static ArrayList<Schedule> getScheduleId(int movieid){return ScheduleDAO.getScheduleId(movieid);}
    public static ArrayList<Seat> getSeats() {
        return SeatDAO.getSeats();
    }
    public static Seat getSeat(int id){
        return SeatDAO.getSeat(id);
    }
    public static ArrayList<Seat> getFreeSeats(int s){
        return SeatDAO.getFreeSeats(s);
    }
    public static ArrayList<Seat> getFreeSeats_byCinemaId(int cinemaid){return SeatDAO.getFreeSeats_byCinemaId(cinemaid);}
        public static ArrayList<Type>getTypeDetails(){
        return TypeDAO.getTypeDetails();
    }
    public static Type getTypeDetail(int id){
        return TypeDAO.getTypeDetail(id);
    }
    public static boolean typeExists(int typeID) {
        return TypeDAO.typeExists(typeID);
    }
}

