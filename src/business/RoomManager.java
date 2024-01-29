package business;

import core.ComboItem;
import core.Helper;
import dao.RoomDao;
import entities.Hotel;
import entities.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private final HotelManager hotelManager;
    private final TypeManager typeManager;
    private final SeasonManager seasonManager;

    public RoomManager(){
        this.roomDao = new RoomDao();
        this.hotelManager = new HotelManager();
        this.typeManager = new TypeManager();
        this.seasonManager = new SeasonManager();
    }

    public Room getById(int roomId){
        return this.roomDao.getById(roomId);
    }

    public boolean saveRoom(Room room){
        if (room.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }

        return this.roomDao.saveRoom(room);
    }

    public ArrayList<Room> fetchAllRooms(){
        return this.roomDao.fetchAllRooms();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> list){
        ArrayList<Object[]> roomRowList = new ArrayList<>();

        for (Room room: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = room.getId();
            rowObject[i++] = this.hotelManager.getById(room.getHotel_id()).getHotel_name();
            rowObject[i++] = this.typeManager.getById(room.getRoom_hotel_type_id()).getType_hotel_name();
            rowObject[i++] = this.seasonManager.getById(room.getRoom_season_id()).getSeason_name();
            rowObject[i++] = room.getRoom_price_adult();
            rowObject[i++] = room.getRoom_price_child();
            rowObject[i++] = room.getRoom_name();
            rowObject[i++] = room.getRoom_bed_count();
            rowObject[i++] = convertBooleanValueToString(room.isRoom_tv());
            rowObject[i++] = convertBooleanValueToString(room.isRoom_minibar());
            rowObject[i++] = convertBooleanValueToString(room.isRoom_console());
            rowObject[i++] = room.getRoom_squarefootage();
            rowObject[i++] = convertBooleanValueToString(room.isRoom_safe());
            rowObject[i++] = convertBooleanValueToString(room.isRoom_projection());
            rowObject[i++] = room.getRoom_stock();

            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

    public String convertBooleanValueToString(boolean value){
        if (value){
            return "Yes";
        } else {
            return "No";
        }
    }

    public ArrayList<ComboItem> getRoomsByHotelId(int hotelId){
        return this.roomDao.getRoomsByHotelId(hotelId);
    }

    public boolean changeStock(int newStock, int roomId){
       return this.roomDao.changeStock(newStock, roomId);
    }

    public ArrayList<Room> customQuery(String hotelName,String hotelCity){
        String query = "SELECT * FROM public.rooms LEFT JOIN public.hotels ON public.rooms.hotel_id = public.hotels.id " +
                "WHERE hotel_name = '" + hotelName + "' ";

        if (hotelCity != null){
            query += "AND hotel_city = '" + hotelCity + "'";
        }

        query += ";";
        System.out.println(query);
        return this.roomDao.queryDatabase(query);
    }
}
