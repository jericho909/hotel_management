package business;

import core.Helper;
import dao.ReservationDao;
import entities.Reservation;
import entities.Room;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int reservationId){
        return this.reservationDao.getById(reservationId);
    }

    public boolean saveReservation(Reservation reservation){
        if (reservation.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }

        return this.reservationDao.saveReservation(reservation);
    }

    public boolean deleteReservation(int reservationId){
        if (this.getById(reservationId) == null){
            Helper.showErrorMessage("Cannot find ID");
            return false;
        }
        return this.reservationDao.deleteReservation(reservationId);
    }

    public ArrayList<Reservation> fetchAllReservations(){
        return this.reservationDao.fetchAllReservations();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> list){
        ArrayList<Object[]> reservationRowList = new ArrayList<>();

        for (Reservation reservation: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getRoom_id();
            rowObject[i++] = reservation.getReservation_start_date();
            rowObject[i++] = reservation.getReservation_end_date();
            rowObject[i++] = reservation.getReservation_guest_name();
            rowObject[i++] = reservation.getReservation_guest_phone();
            rowObject[i++] = reservation.getReservation_total_price();

            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }


}
