package View;


import Controller.NewUserController;
import Model.DTO.Account;
import Model.DTO.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class NewUserLogin {
    public static void userView(){
        NewUserController userController=new NewUserController();
        Scanner sc=new Scanner(System.in);
        boolean h=true;
        while (h) {
            System.out.println("Enter Values for Creating a Account");
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.println("Enter Email: ");
            String email = sc.next();
            System.out.print("Enter Password: ");
            String password = sc.next();
            System.out.print("Enter Phone Number: ");
            String phone = sc.next();
            System.out.print("Enter Year: ");
            int year = sc.nextInt();
            System.out.print("Enter Month: ");
            int month = sc.nextInt();
            System.out.println("Enter Date: ");
            int date = sc.nextInt();
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            LocalDate dateofbirth = LocalDate.of(year, month, date);
            User user = new User(id, email, name, dateofbirth, phone);
            int out = userController.addUser(user);

                Account acc = new Account(email, password, name, dateofbirth, phone, "Customer");
                userController.addNewAccount(acc);
                System.out.println("Enter C to book tickets...");
                char opto = sc.next().charAt(0);
                if (Character.toUpperCase(opto) == 'C') {
                    UserView view = new UserView();
                    view.viewUser();
                }
            h=false;
        }
    }
}
