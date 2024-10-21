package Model.DTO;

public class History {
    private String recordId;
    private int scheduleId;
    private int seatId;
    private String email;
    private int userId;
    private double price;

    public History(String recordId, int scheduleId, int seatId, String email, int userId, double price) {
        super();
        this.recordId = recordId;
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.email = email;
        this.userId = userId;
        this.price = price;
    }
    public History(){}
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "History{" +
                "recordId='" + recordId + '\'' +
                ", scheduleId=" + scheduleId +
                ", seatId=" + seatId +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
