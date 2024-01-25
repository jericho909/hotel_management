package business;

import core.Helper;
import dao.TypeDao;
import entities.Type;
import entities.User;

import java.util.ArrayList;

public class TypeManager {
    private final TypeDao typeDao;
    private final HotelManager hotelManager;

    public TypeManager() {
        this.typeDao = new TypeDao();
        this.hotelManager = new HotelManager();
    }

    public boolean saveType(Type type){
        if (type.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }
        return this.typeDao.saveType(type);
    }

    public ArrayList<Type> fetchAllTypes(){
        return this.typeDao.fetchAllTypes();
    }

    public ArrayList<Type> getByTypeId(int typeId){
        return this.typeDao.getByTypeId(typeId);
    }

    public ArrayList<String> getTypesByHotelId(int hotelId){return this.typeDao.getTypesByHotelId(hotelId);}

    public ArrayList<Object[]> getForTable(int size, ArrayList<Type> list){
        ArrayList<Object[]> typeRowList = new ArrayList<>();

        for (Type type: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = type.getId();
            rowObject[i++] = this.hotelManager.getById(type.getHotel_id()).getHotel_name();
            rowObject[i++] = type.getType_hotel_name();
            typeRowList.add(rowObject);
        }
        return typeRowList;
    }
}
