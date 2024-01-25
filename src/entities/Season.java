package entities;

import java.time.LocalDate;

public class Season {
    int id;
    int hotel_id;
    LocalDate season_start_date;
    LocalDate season_end_date;

    public Season(int hotel_id, LocalDate season_start_date, LocalDate season_end_date) {
        this.hotel_id = hotel_id;
        this.season_start_date = season_start_date;
        this.season_end_date = season_end_date;
    }

    public Season() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getSeason_start_date() {
        return season_start_date;
    }

    public void setSeason_start_date(LocalDate season_start_date) {
        this.season_start_date = season_start_date;
    }

    public LocalDate getSeason_end_date() {
        return season_end_date;
    }

    public void setSeason_end_date(LocalDate season_end_date) {
        this.season_end_date = season_end_date;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", season_start_date=" + season_start_date +
                ", season_end_date=" + season_end_date +
                '}';
    }
}
