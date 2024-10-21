package View;

import Controller.AdminController;
import Model.DAO.CinemaDAO;
import Model.DAO.MovieDAO;
import java.time.*;

import Model.DTO.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    public static void viewAdmin() throws InterruptedException, SQLException, ClassNotFoundException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome admin");
        String userName;
        String password;
        AdminController control=new AdminController();
        System.out.println("Username-->");
        userName=sc.nextLine();
        System.out.println("Password-->");
        password=sc.next();
        if(userName.compareTo("A")==0 && password.compareTo("1")==0){
            while(true){
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
                    case 1:
                    {
                        System.out.println("Revenue for the cinema");
                        int revenue=control.getRevenue();
                        System.out.println(revenue);
                        break;
                    }
                    case 2:{
                        System.out.println("See Cinema");
                        System.out.println("A.Get cinema by id\n" +
                                "B.See all cinemas ");
                        System.out.println("Enter your choice ");
                        char cinemachoice=Character.toUpperCase(sc.next().charAt(0));
                        switch (cinemachoice){
                            case 'A':{
                                System.out.println("Enter cinemaId: ");
                                int cinemaid= sc.nextInt();
                                Cinema cinema=control.getCinema(cinemaid);
                                if(cinema!=null){
                                    System.out.println("Cinema Details: ");
                                    System.out.println("ScreenId "+cinema.getScreenId());
                                    System.out.println("Screen Name "+cinema.getScreenName());
                                    System.out.println("Number of Seats "+cinema.getNoOfSeats());
                                    System.out.println("Type Id "+cinema.getTypeId());
                                }
                                break;
                            }
                            case 'B':{
                               List<Cinema> cinemas=control.getCinemas();

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
                            default: {
                                System.out.println("Enter valid choice");
                            }
                            break;
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Movie details: ");
                        System.out.println("A.add new movie\n" +
                                "B.edit movie\n" +
                                "C.get movie by id\n" +
                                "D.movie exists\n");
                        System.out.println("Enter your choice ");
                        char moviechoice=sc.next().charAt(0);
                        switch (moviechoice){
                            case 'A':{
                                System.out.println("=====================================");
                                System.out.println("         Add a New Movie            ");
                                System.out.println("=====================================");
                                System.out.print("Enter Movie ID: ");
                                int mid = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Movie Name: ");
                                String mname = sc.nextLine();
                                System.out.print("Enter Movie Genre: ");
                                String gen = sc.nextLine();
                                System.out.print("Enter Movie Duration (in minutes): ");
                                float dur = sc.nextFloat();
                                sc.nextLine();
                                System.out.print("Enter Movie Description: ");
                                String de = sc.nextLine();
                                Movie mov = new Movie(mid, mname, gen, dur, de);
                                control.addNewMovie(mov);
                                break;
                            }
                            case 'B':{
                                System.out.println("=====================================");
                                System.out.println("         Edit a Movie                ");
                                System.out.println("=====================================");
                                System.out.print("Enter Movie ID: ");
                                int mid = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter Movie Name: ");
                                String mname = sc.nextLine();
                                System.out.print("Enter Movie Genre: ");
                                String gen = sc.nextLine();
                                System.out.print("Enter Movie Duration (in minutes): ");
                                float dur = sc.nextFloat();
                                sc.nextLine();
                                System.out.print("Enter Movie Description: ");
                                String de = sc.nextLine();
                                Movie mov = new Movie(mid, mname, gen, dur, de);
                                System.out.println("Enter Old Movie Id: ");
                                int oldId= sc.nextInt();
                                control.editMovie(mov,oldId);
                                System.out.println("Movie edited Successfully!!");
                                break;
                            }
                            case 'C':{
                                System.out.println("Get Movie by Id ");
                                System.out.println("Enter Movie Id");
                                int movid=sc.nextInt();
                                Movie movie=control.getMovie(movid);
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
                            case 'D':{
                                System.out.println("Enter Movie Id ");
                                int movieid=sc.nextInt();
                                boolean present= control.movieExists(movieid);
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
                    case 4:{
                        //Reservations:
                        System.out.println("Getting all  Reservations");
                        List<Reservation>reservations= control.getReservations();
                        if(!reservations.isEmpty()){
                            System.out.printf("%-20s %-15s %-10s %-10s%n","Schedule ID" ,"Seat ID ","Email" ,"Guest ID");
                            System.out.println("-------------------------------------------------------------------------------");

                            for(Reservation reservation:reservations){
                                reservation.getScheduleID();
                                reservation.getSeatID();
                                reservation.getEmail();
                                reservation.getUserID();
                            }
                        }
                        else {
                            System.out.println("No Reservation is available!");
                        }
                        break;
                    }
                    case 5:{
                        //Schedule
                        System.out.println("Schedules");
                        System.out.println("A.GET SCHEDULES \n" +
                                "B.ADD NEW SCHEDULE\n" +
                                "C.EDIT SCHEDULE\n" +
                                "D.SCHEDULE EXISTS\n");
                        System.out.println("Enter your choice");
                        char schedulechoice=Character.toUpperCase(sc.next().charAt(0));
                        switch (schedulechoice){
                            case 'A':{
                                ArrayList<Schedule>schedules= control.getSchedule();
                                if(!schedules.isEmpty()){
                                    System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Schedule ID", "Cinema ID", "Movie ID", "Time", "Date");
                                    System.out.println("--------------------------------------------------------------------------");
                                    for (Schedule schedule : schedules) {
                                        System.out.printf("%-15d %-15d %-15d %-15s %-15s%n",
                                                schedule.getScheduleId(),
                                                schedule.getCinemaId(),
                                                schedule.getMovieId(),
                                                schedule.getTime(),
                                                schedule.getDate()
                                        );
                                    }
                                }
                                else{
                                    System.out.println("No Schedules are available");
                                }
                                break;
                            }
                            case 'B':{
                                int scid=sc.nextInt();
                                int cid=sc.nextInt();
                                int moid=sc.nextInt();
                                System.out.print("Enter Year: ");
                                int year = sc.nextInt();
                                System.out.print("Enter Month: ");
                                int month = sc.nextInt();
                                System.out.print("Enter Day: ");
                                int day = sc.nextInt();
                                LocalDate userDate = LocalDate.of(year, month, day);
                                int hour=sc.nextInt();
                                int min=sc.nextInt();
                                LocalTime usertime=LocalTime.of(hour,min);
                                Schedule schedule=new Schedule(scid,cid,moid,userDate,usertime);

                                control.addNewSchedule(schedule);

                                break;
                            }
                            case 'C':{
                                int scid=sc.nextInt();
                                int cid=sc.nextInt();
                                int moid=sc.nextInt();
                                System.out.print("Enter Year: ");
                                int year = sc.nextInt();
                                System.out.print("Enter Month: ");
                                int month = sc.nextInt();
                                System.out.print("Enter Day: ");
                                int day = sc.nextInt();
                                LocalDate userDate = LocalDate.of(year, month, day);
                                int hour=sc.nextInt();
                                int min=sc.nextInt();
                                LocalTime usertime=LocalTime.of(hour,min);
                                Schedule schedule=new Schedule(scid,cid,moid,userDate,usertime);
                                int oldschid=sc.nextInt();

                                control.editSchedule(schedule,oldschid);
                                break;
                            }
                            case 'D':{
                                System.out.println("Enter Schedule ID to check whether Schedule exists or Not");
                                int scheduleid=sc.nextInt();
                                boolean exists=control.scheduleExists(scheduleid);
                                System.out.println(exists);
                                break;
                            }
                            default:{
                                System.out.println("Enter valid choice");
                            }
                        }
                        break;
                    }
                    case 6:{
                        System.out.println("SEATS");
                        System.out.println("A.get seats\n" +
                                "B.getFreeSeats");
                        System.out.println("Enter your choice ");
                        char seatchoice=Character.toUpperCase(sc.next().charAt(0));
                        switch (seatchoice){
                            case 'A':{
                                List<Seat>seats=control.getSeats();
                                if (!seats.isEmpty()) {
                                    System.out.printf("%-10s %-10s %-10s %-10s%n", "Seat ID", "Row", "Column", "Cinema ID");
                                    System.out.println("-----------------------------------------------");

                                    // Loop through each seat and print its details in the formatted table
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
                                List<Seat>freeseats=control.getFreeSeats(scheduleId);
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
                            default:{
                                System.out.println("Enter valid choice");
                                break;
                            }
                        }
                        break;
                    }
                    case 7:{
                        System.out.println("A.add new type\n" +
                                "B.Edit Type\n" +
                                "C.Type Exists\n");
                        System.out.println("Enter your choice ");
                        char typechoice=Character.toUpperCase(sc.next().charAt(0));
                        switch (typechoice){
                            case 'A':{
                                System.out.println("Enter type Id for Adding");
                                int typeid=sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter Type Name");
                                String typename=sc.nextLine();
                                System.out.println("Enter Price");
                                int price= sc.nextInt();
                                Type type=new Type(typeid,typename,price);
                                control.addNewType(type);
                                break;
                            }
                            case 'B':{
                                System.out.println("Enter Type Id for Editing");
                                int typeid=sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter Type Name");
                                String typename=sc.nextLine();
                                System.out.println("Enter Price for type ");
                                int price= sc.nextInt();

                                Type type=new Type(typeid,typename,price);
                                control.editType(type,typeid);
                                System.out.println("Type Edited Successfully!!!");
                                break;
                            }
                            case 'C':{
                                System.out.println("Enter Type ID to check if type available or Not");
                                int typeid= sc.nextInt();
                                boolean typepresent=control.typeExists(typeid);
                                if(typepresent)
                                System.out.println("The type "+typeid+" is Present");
                                else
                                    System.out.println("Type not available");
                                break;
                            }
                        }
                        break;
                    }
                    default:{
                        System.out.println("Enter valid details");
                    }
                }
            }
        }
    }
}
