package Model.DTO;

import java.time.*;

public class Schedule {
    private int scheduleId;
    private int cinemaId;
    private int movieId;
    private LocalDate Date;
    private LocalTime Time;

    public Schedule(int scheduleId, int cinemaId, int movieId, LocalDate date,LocalTime time) {
        this.scheduleId = scheduleId;
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.Date = date;
        this.Time=time;
    }
    public Schedule(){}
    public Schedule(int scheduleId,LocalDate date,LocalTime time){
        this.scheduleId=scheduleId;
        this.Date=date;
        this.Time=time;
    }
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }


    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", cinemaId=" + cinemaId +
                ", movieId=" + movieId +
                ", Date=" + Date +
                ", Time=" + Time +
                '}';
    }
}
