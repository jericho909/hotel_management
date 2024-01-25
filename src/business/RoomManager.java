package business;

import core.Helper;
import dao.RoomDao;
import entities.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
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
            rowObject[i++] = room.getHotel_id();
            rowObject[i++] = room.getRoom_hotel_type_id();
            rowObject[i++] = room.getRoom_season_id();
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
}
