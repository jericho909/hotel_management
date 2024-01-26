package entities;

import java.time.LocalDate;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate season_start_date;
    private LocalDate season_end_date;
    private String season_name;
    private Double season_rate;

    public Season(int hotel_id, LocalDate season_start_date, LocalDate season_end_date, String season_name, Double season_rate) {
        this.hotel_id = hotel_id;
        this.season_start_date = season_start_date;
        this.season_end_date = season_end_date;
        this.season_name = season_name;
        this.season_rate = season_rate;
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

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public Double getSeason_rate() {
        return season_rate;
    }

    public void setSeason_rate(Double season_rate) {
        this.season_rate = season_rate;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", season_start_date=" + season_start_date +
                ", season_end_date=" + season_end_date +
                ", season_name='" + season_name + '\'' +
                ", season_rate=" + season_rate +
                '}';
    }
}
