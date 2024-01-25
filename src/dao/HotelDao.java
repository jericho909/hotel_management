package dao;

import core.DbConnection;
import entities.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    public final Connection connection;


    public HotelDao() {
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<Hotel> fetchAllHotels(){
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.hotels";

            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                hotelArrayList.add(convertDatabaseValueToHotel(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return hotelArrayList;
    }

    public Hotel convertDatabaseValueToHotel(ResultSet rs) throws SQLException{
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setHotel_name(rs.getString("hotel_name"));
        hotel.setHotel_city(rs.getString("hotel_city"));
        hotel.setHotel_adress(rs.getString("hotel_adress"));
        hotel.setHotel_email(rs.getString("hotel_email"));
        hotel.setHotel_phone(rs.getString("hotel_phone"));
        hotel.setHotel_star(rs.getString("hotel_star"));
        hotel.setHotel_freeparking(rs.getBoolean("hotel_freeparking"));
        hotel.setHotel_freewifi(rs.getBoolean("hotel_freewifi"));
        hotel.setHotel_pool(rs.getBoolean("hotel_pool"));
        hotel.setHotel_gym(rs.getBoolean("hotel_gym"));
        hotel.setHotel_concierge(rs.getBoolean("hotel_concierge"));
        hotel.setHotel_spa(rs.getBoolean("hotel_spa"));
        hotel.setHotel_roomservice(rs.getBoolean("hotel_roomservice"));

        return hotel;

    }

    public Hotel getById(int hotelId){
        Hotel hotel = null;
        String query = "SELECT * FROM public.hotels WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                hotel = this.convertDatabaseValueToHotel(rs);
            }
        } catch (SQLException e){

        }
        return hotel;
    }

    public boolean saveHotel(Hotel hotel){
        String query = "INSERT INTO public.hotels (hotel_name, hotel_city, hotel_adress, hotel_email, hotel_phone, " +
                "hotel_star, hotel_freeparking, hotel_freewifi, hotel_pool, hotel_gym, hotel_concierge, hotel_spa, " +
                "hotel_roomservice) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getHotel_name());
            preparedStatement.setString(2, hotel.getHotel_city());
            preparedStatement.setString(3, hotel.getHotel_adress());
            preparedStatement.setString(4, hotel.getHotel_email());
            preparedStatement.setString(5, hotel.getHotel_phone());
            preparedStatement.setString(6, hotel.getHotel_star());
            preparedStatement.setBoolean(7, hotel.isHotel_freeparking());
            preparedStatement.setBoolean(8, hotel.isHotel_freewifi());
            preparedStatement.setBoolean(9, hotel.isHotel_pool());
            preparedStatement.setBoolean(10,hotel.isHotel_gym());
            preparedStatement.setBoolean(11, hotel.isHotel_concierge());
            preparedStatement.setBoolean(12, hotel.isHotel_spa());
            preparedStatement.setBoolean(13, hotel.isHotel_roomservice());

            return preparedStatement.executeUpdate() != -1;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Hotel> customQueryDatabase(String query){
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                hotelArrayList.add(convertDatabaseValueToHotel(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return hotelArrayList;
    }

    public int queryDatabaseForId(String hotelName) {
        String query = "SELECT id FROM public.hotels WHERE hotel_name = ?";
        int id = -1; // Default value if no hotel is found

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotelName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

}
