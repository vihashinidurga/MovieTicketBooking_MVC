package View;

import Controller.UserController;
import Model.DAO.AccountDAO;
import Model.DAO.MovieDAO;
import Model.DAO.SeatDAO;
import Model.DTO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    public static void viewUser(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome User!!");
        String username;
        String  password;
        UserController controller=new UserController();
        AccountDAO dao=new AccountDAO();
        System.out.println("Email --> ");
        username= sc.nextLine();
        System.out.println("Password --> ");
        password=sc.nextLine();
        String verifiedEmail=dao.getEmailIfVerified(username,password);
        //verify User
        if(verifiedEmail!=null){
            System.out.println("Welcome Back "+dao.name(username));
            while (true){
                System.out.println("1.History");
                System.out.println("2.Cinema");
                System.out.println("3.Movie");
                System.out.println("4.Reservation");
                System.out.println("5.Schedule");
                System.out.println("6.Seats");
                System.out.println("7.Type");

                System.out.println("enter you option");
                int option=sc.nextInt();

                switch (option){
                    case 1:{
                        List<History> history = controller.getHistory();
                        if (!history.isEmpty()) {
                            System.out.printf("%-20s %-10s %-10s %-25s %-10s%n", "Record ID", "Schedule ID", "Seat ID", "Email", "User ID");
                            System.out.println("-------------------------------------------------------------");
                            for (History his : history) {
                                System.out.printf("%-20s %-10d %-10d %-25s %-10d%n",
                                        his.getRecordId(),
                                        his.getScheduleId(),
                                        his.getSeatId(),
                                        his.getEmail(),
                                        his.getUserId());
                            }
                        } else {
                            System.out.println("No History Found");
                        }
                        break;
                    }
                    case 2:{
                        System.out.println("All Cinemas");
                        List<Cinema> cinemas=controller.getCinemas();

                        if(!cinemas.isEmpty()){
                            System.out.printf("%-10s %-20s %-15s %-10s%n", "Screen ID", "Screen Name", "No. of Seats", "Type ID");
                            System.out.println("--------------------------------------------------------------");
                            for (Cinema cinema : cinemas) {
                                System.out.printf("%-10d %-20s %-15d %-10d%n",
                                        cinema.getScreenId(),
                                        cinema.getScreenName(),
                                        cinema.getNoOfSeats(),
                                        cinema.getTypeId());
                            }
                        }
                        else {
                            System.out.println("No cinemas found");
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("A.Get Latest Movie\n" +
                                "B.Get Movie by Id\n" +
                                "C.Get All Movies\n" +
                                "D.Movie Exists");
                        System.out.println("Enter your choice");
                        char moviechoice=sc.next().charAt(0);
                        switch (moviechoice){
                            case 'A':{
                                System.out.println("Getting All Latest Movies");
                                List<Movie>movies=controller.getLatestMovies();
                                if(!movies.isEmpty()){
                                    System.out.printf("%-10s %-20s %-15s %-15s %-30s%n", "Movie ID", "Movie Name", "Movie Genre", "Movie Duration", "Movie Description");
                                    System.out.println("------------------------------------------------------------------");
                                    for(Movie movie:movies){
                                        System.out.printf("%-10d %-20s %-15s %-15s %-30s%n",
                                                movie.getMovieId(),
                                                movie.getMovieName(),
                                                movie.getGenre(),
                                                movie.getDuration(),
                                                movie.getDesc());

                                    }
                                }
                                else{
                                    System.out.println("No latest Movies");
                                }
                                break;
                            }
                            case 'B':{
                                System.out.println("Get Movie by Id ");
                                System.out.println("Enter Movie Id");
                                int movid=sc.nextInt();
                                Movie movie=controller.getMovie(movid);
                                if(movie!=null){
                                    System.out.println("Movie Details: ");
                                    System.out.println("Movie Id "+movie.getMovieId());
                                    System.out.println("Movie Name "+movie.getMovieName());
                                    System.out.println("Movie Genre "+movie.getGenre());
                                    System.out.println("Movie duration"+movie.getDuration());
                                    System.out.println("Movie Description "+movie.getDesc());
                                }
                                break;
                            }
                            case 'C':{
                                System.out.println("Getting All Movies");
                                List<Movie>movies=controller.getMovies();
                                if(!movies.isEmpty()){
                                    System.out.printf("%-10s %-35s %-15s %-15s %-30s%n", "Movie ID", "Movie Name", "Movie Genre", "Movie Duration", "Movie Description");
                                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    for(Movie movie:movies){
                                        System.out.printf("%-10d %-35s %-15s %-15s %-30s%n",
                                                movie.getMovieId(),
                                                movie.getMovieName(),
                                                movie.getGenre(),
                                                movie.getDuration(),
                                                movie.getDesc());
                                    }
                                }
                                else{
                                    System.out.println("No latest Movies");
                                }
                                break;
                            }
                            case 'D':{
                                System.out.println("Enter Movie Id ");
                                int movieid=sc.nextInt();
                                boolean present= controller.movieExists(movieid);
                                if(present){
                                    System.out.println("Movie with ID "+movieid+" is Present");
                                }
                                else {
                                    System.out.println("Movie is not available");
                                }
                                break;
                            }
                            default:{
                                System.out.println("Enter valid option");
                            }
                            break;
                            }
                            break;
                        }
                    case 4: {
                        System.out.println("Enter details for adding reservation: ");
                        System.out.print("Enter user Id: ");
                        int userId = sc.nextInt();
                        sc.nextLine();
                        String email = username;
                        System.out.print("Enter Movie ID of your choice: ");
                        int movieId = sc.nextInt();
                        ArrayList<Schedule> scheduleIds = controller.getScheduleId(movieId);
                        if (!scheduleIds.isEmpty()) {
                            System.out.printf("%-15s %-15s %-15s\n", "Schedule ID", "Schedule Date", "Schedule Time");
                            System.out.println("-----------------------------------------------");
                            for (Schedule schedule : scheduleIds) {
                                System.out.printf("%-15d %-15s %-15s\n",
                                        schedule.getScheduleId(),
                                        schedule.getDate(),
                                        schedule.getTime());
                            }
                        }
                        System.out.print("Choose any Schedule Id to book a reservation: ");
                        int scheduleId = sc.nextInt();
                        int cd = controller.cinemaId(scheduleId);
                        ArrayList<Seat>seats_for_cinema=controller.getFreeSeats_byCinemaId(cd);
                        if(!seats_for_cinema.isEmpty()){
                            System.out.printf("%-10s %-10s %-10s\n", "Seat ID", "Row", "Column");
                            System.out.println("-----------------------------------");
                            for (Seat seat : seats_for_cinema) {
                                System.out.printf("%-10d %-10c %-10c\n",
                                        seat.getSeatId(),
                                        seat.getRow(),
                                        seat.getColumn());
                            }
                        }
                        System.out.print("Enter No Of Seats to be booked: ");
                        int nse = sc.nextInt();
                        for (int i = 1; i <= nse; i++) {
                            System.out.print("Enter Seat ID for seat " + i + ": ");
                            int seatId = sc.nextInt();
                            sc.nextLine();
                            Reservation res = new Reservation(scheduleId, seatId, email, userId, cd);
                            controller.addReservation(res, cd);
                        }
                        int price = controller.calculatePrice(cd, nse);
                        System.out.println("Price for " + nse + " seat(s) is: " + price);
                        break;
                    }
                    case 5:{
                        System.out.println("Schedule Exists: ");
                        System.out.println("Enter Schedule ID to check whether Schedule exists or Not");
                        int scheduleid=sc.nextInt();
                        boolean exists=controller.scheduleExists(scheduleid);
                        if(exists)
                            System.out.printf("Schedule with "+scheduleid+" exists");
                        else
                            System.out.printf("Schedule doesn't exists");
                        break;
                    }
                    case 6:{
                        System.out.println("Seats");
                        System.out.println("A.get seats\n" +
                                "B.get Free Seats\n" +
                                "C.get Seat by ID");
                        System.out.println("Enter your choice ");
                        char seatchoice=Character.toUpperCase(sc.next().charAt(0));
                        switch (seatchoice){
                            case 'A':{
                                List<Seat>seats=controller.getSeats();
                                if (!seats.isEmpty()) {
                                    System.out.printf("%-10s %-10s %-10s %-10s%n", "Seat ID", "Row", "Column", "Cinema ID");
                                    System.out.println("-----------------------------------------------");
                                    for (Seat seat : seats) {
                                        System.out.printf("%-10d %-10s %-10d %-10d%n",
                                                seat.getSeatId(),
                                                seat.getRow(),
                                                seat.getColumn(),
                                                seat.getCinemaId()
                                        );
                                    }
                                } else {
                                    System.out.println("No seats found.");
                                }
                                break;
                            }
                            case 'B':{
                                System.out.println("Enter Schedule ID to get free seats");
                                int scheduleId=sc.nextInt();
                                List<Seat>freeseats=controller.getFreeSeats(scheduleId);
                                if (!freeseats.isEmpty()) {
                                    System.out.printf("%-10s %-10s %-10s %-10s%n", "Seat ID", "Row", "Column", "Cinema ID");
                                    System.out.println("-----------------------------------------------");
                                    for (Seat freeseat : freeseats) {
                                        System.out.printf("%-10d %-10s %-10d %-10d%n",
                                                freeseat.getSeatId(),
                                                freeseat.getRow(),
                                                freeseat.getColumn(),
                                                freeseat.getCinemaId()
                                        );
                                    }
                                } else {
                                    System.out.println("No seats found.");
                                }
                                break;
                            }
                            case 'C':{
                                System.out.println("Enter Seat Id to get Seat details");
                                int seatid= sc.nextInt();
                                Seat seat=controller.getSeat(seatid);
                                if(seat!=null){
                                    System.out.println("Seat Details ");
                                    System.out.println("Seat Id "+seatid);
                                    System.out.println("Seat Column "+seat.getColumn());
                                    System.out.println("Seat Row "+seat.getRow());
                                    System.out.println("Seat Cinema ID "+seat.getCinemaId());
                                }
                                break;
                            }
                            case 7:{
                                System.out.println("Type Details ");
                                System.out.println("A.Get Type details\n" +
                                        "B.Get type by Id\n" +
                                        "C.Type Exists");
                                char typechoice=Character.toUpperCase(sc.next().charAt(0));
                                switch (typechoice){
                                    case 'A':{
                                        System.out.println("Getting all Type Details.........");
                                        List<Type>types=controller.getTypeDetails();
                                        if(!types.isEmpty()){
                                            System.out.printf("%-5s %-5s %-5");
                                            System.out.println("-------------------------------");
                                            for (Type type:types){
                                                System.out.printf("%-5s %-5s %-5",
                                                        type.getTypeId(),
                                                        type.getType(),
                                                        type.getPrice());
                                            }
                                        }
                                        break;
                                    }
                                    case'B':{
                                        System.out.println("Enter Type id to get details");
                                        int typeid= sc.nextInt();
                                        Type type=controller.getTypeDetail(typeid);
                                        if(type!=null){
                                            System.out.println("Type Id "+type.getTypeId());
                                            System.out.println("Type Name "+type.getType());
                                            System.out.println("Type Price "+type.getPrice());
                                        }
                                        break;
                                    }
                                    case 'C':{
                                        System.out.println("Enter Type ID to check if type available or Not");
                                        int typeid= sc.nextInt();
                                        boolean typepresent=controller.typeExists(typeid);
                                        if(typepresent)
                                            System.out.println("The type "+typeid+" is Present");
                                        else
                                            System.out.println("Type not available");
                                        break;
                                    }
                                }
                            }
                            default:{
                                System.out.println("Enter valid choice");
                                break;
                            }
                        }
                    }
                }
            }
        }
        else{
            System.out.println("Please Enter valid Email/Password");
        }
    }
}
