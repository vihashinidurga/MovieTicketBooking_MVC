package Model.DTO;

import java.time.LocalDate;
import java.util.Date;

public class User {
    private int userID;
    private String userEmail;
    private String userName;
    private LocalDate userDOB;
    private String userPhNum;

    public User(int userID, String userEmail, String userName, LocalDate userDOB, String userPhNum) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userDOB = userDOB;
        this.userPhNum = userPhNum;
    }
    public User(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(LocalDate userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserPhNum() {
        return userPhNum;
    }

    public void setUserPhNum(String userPhNum) {
        this.userPhNum = userPhNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userDOB=" + userDOB +
                ", userPhNum='" + userPhNum + '\'' +
                '}';
    }
}

