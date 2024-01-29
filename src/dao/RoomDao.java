package dao;

import core.ComboItem;
import core.DbConnection;
import entities.Hotel;
import entities.Room;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    public final Connection connection;

    public RoomDao() {
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<Room> fetchAllRooms(){
        ArrayList<Room> roomArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.rooms";

            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                roomArrayList.add(convertDatabaseValueToRoom(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return roomArrayList;
    }

    public Room convertDatabaseValueToRoom(ResultSet rs) throws SQLException{
        Room room = new Room();

        room.setId(rs.getInt("id"));
        room.setHotel_id(rs.getInt("hotel_id"));
        room.setRoom_hotel_type_id(rs.getInt("room_hotel_type_id"));
        room.setRoom_season_id(rs.getInt("room_season_id"));
        room.setRoom_price_adult(rs.getDouble("room_price_adult"));
        room.setRoom_price_child(rs.getDouble("room_price_child"));
        room.setRoom_name(rs.getString("room_name"));
        room.setRoom_bed_count(rs.getInt("room_bed_count"));
        room.setRoom_tv(rs.getBoolean("room_tv"));
        room.setRoom_minibar(rs.getBoolean("room_minibar"));
        room.setRoom_console(rs.getBoolean("room_console"));
        room.setRoom_squarefootage(rs.getInt("room_squarefootage"));
        room.setRoom_safe(rs.getBoolean("room_safe"));
        room.setRoom_projection(rs.getBoolean("room_projection"));
        room.setRoom_stock(rs.getInt("room_stock"));

        return room;
    }

    public Room getById(int roomId){
        Room room = null;
        String query = "SELECT * FROM public.rooms WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                room = convertDatabaseValueToRoom(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return room;
    }

    public boolean saveRoom(Room room){
        String query = "INSERT INTO public.rooms (hotel_id, room_hotel_type_id, room_season_id, room_price_adult, " +
                "room_price_child, room_name, room_bed_count, room_tv, room_minibar, room_console, room_squarefootage, " +
                "room_safe, room_projection, room_stock) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room.getHotel_id());
            preparedStatement.setInt(2, room.getRoom_hotel_type_id());
            preparedStatement.setInt(3, room.getRoom_season_id());
            preparedStatement.setDouble(4, room.getRoom_price_adult());
            preparedStatement.setDouble(5, room.getRoom_price_child());
            preparedStatement.setString(6, room.getRoom_name());
            preparedStatement.setInt(7, room.getRoom_bed_count());
            preparedStatement.setBoolean(8, room.isRoom_tv());
            preparedStatement.setBoolean(9, room.isRoom_minibar());
            preparedStatement.setBoolean(10, room.isRoom_console());
            preparedStatement.setInt(11, room.getRoom_squarefootage());
            preparedStatement.setBoolean(12, room.isRoom_safe());
            preparedStatement.setBoolean(13, room.isRoom_projection());
            preparedStatement.setInt(14, room.getRoom_stock());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<ComboItem> getRoomsByHotelId(int hotelId) {
        int id;
        String roomName;
        String roomStr;
        int roomStock;
        ArrayList<ComboItem> roomArrayList = new ArrayList<>();
        String query = "SELECT id, room_name, room_stock  FROM public.rooms WHERE hotel_id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                roomName = rs.getString("room_name");
                roomStock = rs.getInt("room_stock");
                roomStr = roomName + ": " + roomStock;
                roomArrayList.add(new ComboItem(id, roomStr));

            }
        } catch (SQLException e) {

        }
        return roomArrayList;
    }

    public boolean changeStock(int newStock, int roomId){
        String query = " UPDATE public.rooms SET room_stock = ? WHERE id = ? ";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,newStock);
            preparedStatement.setInt(2, roomId);
            return preparedStatement.executeUpdate() != 1;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public ArrayList<Room> queryDatabase(String query){
        ArrayList<Room> roomArrayList = new ArrayList<>();

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                roomArrayList.add(convertDatabaseValueToRoom(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return roomArrayList;
    }

}
