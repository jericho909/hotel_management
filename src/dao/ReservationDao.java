package dao;

import core.DbConnection;
import entities.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    public final Connection connection;

    public ReservationDao() {
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<Reservation> fetchAllReservations() {
        ArrayList<Reservation> reservationArrayList = new ArrayList<>();
        try{
            String query = "SELECT * FROM public.reservation;";

            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                reservationArrayList.add(convertDatabaseValueToReservation(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return reservationArrayList;
    }

    public Reservation convertDatabaseValueToReservation(ResultSet rs) throws SQLException{
        Reservation reservation = new Reservation();

        reservation.setId(rs.getInt("id"));
        reservation.setRoom_id(rs.getInt("room_id"));
        reservation.setReservation_start_date(LocalDate.parse(rs.getString("reservation_str_date")));
        reservation.setReservation_end_date(LocalDate.parse(rs.getString("reservation_end_date")));
        reservation.setReservation_guest_name(rs.getString("reservation_guest_name"));
        reservation.setReservation_guest_phone(rs.getString("reservation_guest_phone"));
        reservation.setReservation_total_price(rs.getDouble("reservation_total_price"));

        return reservation;
    }

    public Reservation getById(int reservationId){
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                reservation = convertDatabaseValueToReservation(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return reservation;
    }

    public boolean saveReservation(Reservation reservation){
        String query = "INSERT INTO public.reservation (room_id, reservation_str_date, reservation_end_date, " +
                "reservation_guest_name, reservation_guest_phone, reservation_total_price) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getRoom_id());
            preparedStatement.setDate(2, Date.valueOf(reservation.getReservation_start_date()));
            preparedStatement.setDate(3, Date.valueOf(reservation.getReservation_end_date()));
            preparedStatement.setString(4, reservation.getReservation_guest_name());
            preparedStatement.setString(5,reservation.getReservation_guest_phone());
            preparedStatement.setDouble(6, reservation.getReservation_total_price());

            return preparedStatement.executeUpdate() != -1;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean editReservation(Reservation reservation){
        String query = "UPDATE public.reservation SET room_id = ?, reservation_str_date = ?, reservation_end_date = ?, " +
                "reservation_guest_name = ?, reservation_guest_phone = ?, reservation_total_price = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getRoom_id());
            preparedStatement.setDate(2, Date.valueOf(reservation.getReservation_start_date()));
            preparedStatement.setDate(3, Date.valueOf(reservation.getReservation_end_date()));
            preparedStatement.setString(4, reservation.getReservation_guest_name());
            preparedStatement.setString(5,reservation.getReservation_guest_phone());
            preparedStatement.setDouble(6, reservation.getReservation_total_price());
            preparedStatement.setInt(7, reservation.getId());
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteReservation(int reservationId){
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

}
