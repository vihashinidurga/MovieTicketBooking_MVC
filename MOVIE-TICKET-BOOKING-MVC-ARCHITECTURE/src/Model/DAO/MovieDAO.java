package Model.DAO;

import Model.DTO.Movie;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;
    public static ArrayList<Movie> getLatestMovies(){
        ArrayList<Movie>movies=new ArrayList<Movie>();

        int movieId;
        String movieName;
        String genre;
        float duration;
        String desc;

        try {
            query ="Select * from mvc_movie.movie where MovieId=(select max(movieId)from movie);";

            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                movieId = rs.getInt(1);
                movieName = rs.getString(2);
                genre = rs.getString(3);
                duration = rs.getFloat(4);
                desc = rs.getString(5);
                movies.add(new Movie(movieId, movieName, genre, duration, desc));
            }
        }catch (SQLException s){
            System.out.println("SQL EXCEPTION");
            s.printStackTrace();
        }
        return movies;
    }
    public static void addNewMovie(Movie movie){
        query = "INSERT INTO mvc_movie.movie (MovieID, MovieName, Genre, Duration, Description) VALUES (?, ?, ?, ?, ?);";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, movie.getMovieId());
            pst.setString(2, movie.getMovieName());
            pst.setString(3, movie.getGenre());
            pst.setFloat(4, movie.getDuration());
            pst.setString(5, movie.getDesc());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("=====================================");
                System.out.println("   Movie Added Successfully!!!       ");
                System.out.println("=====================================");
            } else {
                System.out.println("Failed to add movie.");
            }

        }
        catch (SQLException se){
            System.out.println("SQL EXCEPTION");
            se.printStackTrace();
        }
    }
    public static void editMovie(Movie movie,int oldMovId){
        query = "UPDATE mvc_movie.movie SET MovieName = '" + movie.getMovieName()
                + "', Genre = '" + movie.getGenre() + "', " + " Link =  '" + "', Duration = '"
                + movie.getDuration() + "', MainChar = '" + "', Description = '" + movie.getDesc()
                + "' where movieID = '"+oldMovId+"';";
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            int rs=st.executeUpdate(query);
        }
        catch (SQLException se){
            System.out.println("SQL EXCEPTION");
            se.printStackTrace();
        }
    }
      public static Movie getMovie(int id){

          Movie movie=null;
          int movieId;
          String movieName;
          String genre;
          float duration;
          String desc;

          try {
              query="select * from mvc_movie.movie where MovieID= '" + id + "'";

              Connection con = DriverManager.getConnection(url, username, password);
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(query);

              while (rs.next()) {
                  movieId = rs.getInt(1);
                  movieName = rs.getString(2);
                  genre = rs.getString(3);
                  duration = rs.getFloat(4);
                  desc = rs.getString(5);
                  movie=new Movie(movieId,movieName,genre,duration,desc);
              }
          }catch (SQLException s){
              System.out.println("SQL EXCEPTION");
              s.printStackTrace();
          }
          return movie;
    }
    public static ArrayList<Movie> getMovies(){
        ArrayList<Movie>movies=new ArrayList<Movie>();

        int movieId;
        String movieName;
        String genre;
        float duration;
        String desc;

        try {
            query = "select * from mvc_movie.movie";

            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                movieId = rs.getInt(1);
                movieName = rs.getString(2);
                genre = rs.getString(3);
                duration = rs.getFloat(4);
                desc = rs.getString(5);
                movies.add(new Movie(movieId, movieName, genre, duration, desc));
            }
        }catch (SQLException s){
            System.out.println("SQL EXCEPTION");
            s.printStackTrace();
        }
        return movies;
    }
    public static int getMovieId(String name){
        int id=0;
        try{
            query="select movieid from mvc_movie.movie where moviename= '"+name+"'";

            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            while (rs.next()){
                id=rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("EXCEPTION "+e.getMessage());
        }

        return id;
    }
    public static boolean movieExists(int movieId){

        try{
            query="select movieID from mvc_movie.movie where movieID='" + movieId + "'";
            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                return true;
            }
        } catch (SQLException se){
            System.out.println("EXCEPTION");
            se.printStackTrace();
        }
        return false;
    }
}
