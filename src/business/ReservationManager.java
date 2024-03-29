package business;

import core.Helper;
import dao.HotelDao;
import dao.ReservationDao;
import dao.RoomDao;
import entities.Reservation;
import entities.Room;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    private final HotelDao hotelDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
        this.roomDao = new RoomDao();
        this.hotelDao = new HotelDao();
    }
    //mostly same as hotelManager, if no comment is given check hotelManager for explanation

    public Reservation getById(int reservationId){
        return this.reservationDao.getById(reservationId);
    }

    public boolean saveReservation(Reservation reservation){
        if (reservation.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }

        return this.reservationDao.saveReservation(reservation);
    }
    //cannot edit a value if it doesn't exist
    public boolean editReservation(Reservation reservation){
        if (this.getById(reservation.getId())  == null ){
            Helper.showErrorMessage("ID not found.");
        }

        return this.reservationDao.editReservation(reservation);
    }

    //cannot delete a value from a db if it doesn't exist
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
            rowObject[i++] = this.hotelDao.getById(this.roomDao.getById(reservation.getRoom_id()).getHotel_id()).getHotel_name() + " / " + this.roomDao.getById(reservation.getRoom_id()).getRoom_name();
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
