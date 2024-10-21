package Controller;

import Model.DAO.AccountDAO;
import Model.DAO.UserDAO;
import Model.DTO.Account;
import Model.DTO.User;

public class NewUserController {
    public static int addUser(User user){
        return UserDAO.addNewUser(user);
    }
    public static void addNewAccount(Account account){
        AccountDAO.addNewAccount(account);
    }
}
