package Model.DTO;

public class Cinema {
    private int screenId;
    private String screenName;
    private int noOfSeats;
    private int typeId;

    public Cinema(int screenId, String screenName, int noOfSeats, int typeId) {
        super();
        this.screenId = screenId;
        this.screenName = screenName;
        this.noOfSeats = noOfSeats;
        this.typeId = typeId;
    }

    public Cinema(){}

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ScreenDTO{" +
                "screenId='" + screenId + '\'' +
                ", screenName='" + screenName + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", typeId='" + typeId + '\'' +
                '}';
    }

}
