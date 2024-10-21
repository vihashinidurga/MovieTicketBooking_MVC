package Model.DTO;

public class Seat {
    private int seatId;
    private char row;
    private char column;
    private int cinemaId;

    public Seat(int seatId, char row, char column, int cinemaId) {
        this.seatId = seatId;
        this.row = row;
        this.column = column;
        this.cinemaId = cinemaId;
    }
    public Seat(){}

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", row=" + row +
                ", column=" + column +
                ", cinemaId=" + cinemaId +
                '}';
    }
}
