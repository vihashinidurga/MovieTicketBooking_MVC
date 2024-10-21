package Model.DTO;

import java.time.LocalDate;

public class Account {
    private String email;
    private String password;
    private String accName;
    private LocalDate DOB;
    private String phoneNum;
    private String Type; //type of user's account

    public Account(String email, String password, String accName, LocalDate DOB, String phoneNum, String type) {
        super();
        this.email = email;
        this.password = password;
        this.accName = accName;
        this.DOB = DOB;
        this.phoneNum = phoneNum;
        Type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accName='" + accName + '\'' +
                ", DOB=" + DOB +
                ", phoneNum='" + phoneNum + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
