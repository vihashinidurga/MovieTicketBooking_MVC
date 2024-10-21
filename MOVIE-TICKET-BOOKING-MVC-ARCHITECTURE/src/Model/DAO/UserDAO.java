package Model.DAO;

import Model.DTO.User;

import java.sql.*;
import java.time.LocalDate;

public class UserDAO {
    private static String url = "jdbc:mysql://localhost:3307/mvc_movie";
    private static String username = "root";
    private static String password = "Durga@2004";
    private static String query;

    public static int addNewUser(User user){
        int userId;
        String userMail;
        String name;
        LocalDate dob;
        String phone;
        int op=-1;
        String query;
        try{
            Date date=Date.valueOf(user.getUserDOB());
            query="INSERT INTO mvc_movie.user (UserId, UserEmail, UserName, UserDOB, UserPhone) VALUES (?, ?, ?, ?, ?)";
            Connection con= DriverManager.getConnection(url,username,password);
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,user.getUserID());
            pst.setString(2,user.getUserEmail());
            pst.setString(3,user.getUserName());
            pst.setDate(4,date);
            pst.setString(5,user.getUserPhNum());

            int rs=pst.executeUpdate();

            if(rs>0){
                op=rs;
            }else{
                System.out.println("Failed to add User");
            }
        }catch (SQLException se){
            System.out.println();
        }
        return op;
    }
}
