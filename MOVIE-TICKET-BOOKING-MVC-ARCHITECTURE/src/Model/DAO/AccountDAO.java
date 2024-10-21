package Model.DAO;

import Model.DTO.Account;

import java.sql.*;

public class AccountDAO {
    static String url="jdbc:mysql://localhost:3307/mvc_movie";
    static String username="root";
    static String password="Durga@2004";

     public static void addNewAccount(Account acc){
         String query;
         Date date= Date.valueOf(acc.getDOB());
        try{
            query="INSERT INTO mvc_movie.account (Email, Pass, AccountName, DOB, Phone, Type) VALUES (?, ?, ?, ?, ?, ?)";
            Connection con=DriverManager.getConnection(url,username,password);
            PreparedStatement pst=con.prepareStatement(query);

            pst.setString(1,acc.getEmail());
            pst.setString(2,acc.getPassword());
            pst.setString(3, acc.getAccName());
            pst.setDate(4,date);
            pst.setString(5,acc.getPhoneNum());
            pst.setString(6, acc.getType());

            int rs=pst.executeUpdate();

            if(rs>0){
                System.out.println("=====================================");
                System.out.println("   Account Added Successfully!!!     ");
                System.out.println("=====================================");
            }else{
                System.out.println("Failed to add Account");
            }
        }
        catch(SQLException e){
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }
    }
    public static String getEmailIfVerified(String email, String pass) {
        String query;
        String verifiedEmail = null;

        try {
            query = "SELECT Email FROM mvc_movie.account WHERE Email = ? AND Pass = ?";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                verifiedEmail = rs.getString("Email");
            }
        } catch (SQLException e) {
            System.out.println("EXCEPTION OCCURRED");
            e.printStackTrace();
        }
        return verifiedEmail;
    }

    public static String name(String email) {
        String query;
        String name = null;
        try {
            query = "select AccountName from mvc_movie.account where Email=? ";
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,email);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                name = rs.getString("AccountName");
            }
        } catch (SQLException e) {
            System.out.println("EXCEPTION OCCURED ");
            e.printStackTrace();
        }
        return name;
    }
    public static int userid(String email){
         String query;
         int num=-1;
         try{
             query="select UserID from user where UserEmail='"+ email +"'";
             Connection con=DriverManager.getConnection(url,username,password);
             Statement st = con.createStatement();

             ResultSet rs = st.executeQuery(query);
             while (rs.next()) {
                 num = rs.getInt(1);
             }
         }catch (SQLException e){
             System.out.println("SQLEXCEPTION");
             e.printStackTrace();
         }
         return num;
    }
}
