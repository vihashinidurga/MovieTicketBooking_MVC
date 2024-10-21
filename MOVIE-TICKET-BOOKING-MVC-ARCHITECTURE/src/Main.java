//import View.AdminView;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        System.out.println("MOVIEEE");
//        boolean check=true;
//
//        while(check){
//            System.out.println("1.ADMIN login");
//            System.out.println("2.USER LOGIN");
//            System.out.println("3.USER SIGN UP");
//
//            System.out.println("enter you role");
//            int choice=sc.nextInt();
//            System.out.println();
//            if(choice==1){
//                AdminView view=new AdminView();
//            }
//            else if(choice==2){
//                System.out.println("Uset detss");
//            }
//            else{
//                check=false;
//            }
//        }
//    }
//}
import View.AdminView;
import View.NewUserLogin;
import View.UserView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to the Movie Booking System");
        System.out.println("Please select an option:");
        System.out.println("1. Admin View");
        System.out.println("2. Login as Existing User");
        System.out.println("3.Create a new Account");
        System.out.println("4.Exiting System...........");
        choice = sc.nextInt();

        switch(choice) {
            case 1:
                AdminView.viewAdmin();
                break;
            case 2:
                UserView.viewUser();
                break;
            case 3:
                NewUserLogin.userView();
                break;
            case 4:
                System.out.println("Exiting system. Thank you!");
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}
