package business;

import core.Helper;
import dao.HotelDao;
import entities.Hotel;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int hotelId){
        return this.hotelDao.getById(hotelId);
    }

    public boolean saveHotel(Hotel hotel){
        //if the hotel given to the method contains any id other than 0,
        // that means the hotel exists in the database so the save function would be wrong.
        if (hotel.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }

        return this.hotelDao.saveHotel(hotel);
    }

    public ArrayList<Hotel> fetchAllHotels(){
        return this.hotelDao.fetchAllHotels();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> list){
        ArrayList<Object[]> hotelRowList = new ArrayList<>();
        //we create rowObjects for all the values we want to be displayed in the tables
        for (Hotel hotel: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getHotel_name();
            rowObject[i++] = hotel.getHotel_city();
            rowObject[i++] = hotel.getHotel_adress();
            rowObject[i++] = hotel.getHotel_email();
            rowObject[i++] = hotel.getHotel_phone();
            rowObject[i++] = hotel.getHotel_star();
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_freeparking());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_freewifi());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_pool());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_gym());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_concierge());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_spa());
            rowObject[i++] = convertBooleanValueToString(hotel.isHotel_roomservice());
            hotelRowList.add(rowObject);
        }
        return hotelRowList;
    }

    //displaying true or false is not good for UX, so we convert them to yes or no
    public String convertBooleanValueToString(boolean value){
        if (value){
            return "Yes";
        } else {
            return "No";
        }
    }

    public ArrayList<Hotel> customQueryDatabase(String query){
        return this.hotelDao.customFetchQueryDatabase(query);
    }

    public int queryDatabaseForId(String hotelName){
        return this.hotelDao.queryDatabaseForId(hotelName);
    }
}
