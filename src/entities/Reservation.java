package entities;

import java.time.LocalDate;

public class Reservation {
    int id;
    int room_id;
    LocalDate reservation_start_date;
    LocalDate reservation_end_date;
    String reservation_guest_name;
    String reservation_guest_phone;
    Double reservation_total_price;

    public Reservation() {
    }

    public Reservation(int room_id, LocalDate reservation_start_date, LocalDate reservation_end_date, String reservation_guest_name, String reservation_guest_phone, Double reservation_total_price) {
        this.room_id = room_id;
        this.reservation_start_date = reservation_start_date;
        this.reservation_end_date = reservation_end_date;
        this.reservation_guest_name = reservation_guest_name;
        this.reservation_guest_phone = reservation_guest_phone;
        this.reservation_total_price = reservation_total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getReservation_start_date() {
        return reservation_start_date;
    }

    public void setReservation_start_date(LocalDate reservation_start_date) {
        this.reservation_start_date = reservation_start_date;
    }

    public LocalDate getReservation_end_date() {
        return reservation_end_date;
    }

    public void setReservation_end_date(LocalDate reservation_end_date) {
        this.reservation_end_date = reservation_end_date;
    }

    public String getReservation_guest_name() {
        return reservation_guest_name;
    }

    public void setReservation_guest_name(String reservation_guest_name) {
        this.reservation_guest_name = reservation_guest_name;
    }

    public String getReservation_guest_phone() {
        return reservation_guest_phone;
    }

    public void setReservation_guest_phone(String reservation_guest_phone) {
        this.reservation_guest_phone = reservation_guest_phone;
    }

    public Double getReservation_total_price() {
        return reservation_total_price;
    }

    public void setReservation_total_price(Double reservation_total_price) {
        this.reservation_total_price = reservation_total_price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", reservation_start_date=" + reservation_start_date +
                ", reservation_end_date=" + reservation_end_date +
                ", reservation_guest_name='" + reservation_guest_name + '\'' +
                ", reservation_guest_phone='" + reservation_guest_phone + '\'' +
                ", reservation_total_price=" + reservation_total_price +
                '}';
    }
}
