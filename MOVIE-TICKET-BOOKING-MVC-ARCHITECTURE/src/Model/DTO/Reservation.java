package Model.DTO;

public class Reservation {
    private int ScheduleID;
    private int SeatID;
    private String Email;
    private int userID;
    private int numberOfSeats;

    public Reservation( int scheduleID, int seatID, String email, int userID,int numberOfSeats) {
        ScheduleID = scheduleID;
        SeatID = seatID;
        Email = email;
        this.userID = userID;
        this.numberOfSeats=numberOfSeats;
    }
    public Reservation( int scheduleID, int seatID, String email, int userID,int numberOfSeats,int cd) {
        ScheduleID = scheduleID;
        SeatID = seatID;
        Email = email;
        this.userID = userID;
        this.numberOfSeats=numberOfSeats;
        cd=cd;
    }
    public Reservation(){}
    public int getScheduleID() {
        return ScheduleID;
    }

    public void setScheduleID(int scheduleID) {
        ScheduleID = scheduleID;
    }

    public int getSeatID() {
        return SeatID;
    }

    public void setSeatID(int seatID) {
        SeatID = seatID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ScheduleID=" + ScheduleID +
                ", SeatID=" + SeatID +
                ", Email='" + Email + '\'' +
                ", userID=" + userID +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
