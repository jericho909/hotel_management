package business;

import core.Helper;
import dao.TypeDao;
import entities.Type;

import java.util.ArrayList;

public class TypeManager {
    private final TypeDao typeDao;

    public TypeManager() {
        this.typeDao = new TypeDao();
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

    public Type getById(int typeId){
        return this.typeDao.getById(typeId);
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> typeRowList = new ArrayList<>();

        for (Type type: this.fetchAllTypes()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = type.getId();
            rowObject[i++] = type.getHotel_id();
            rowObject[i++] = type.getType_hotel_name();
            typeRowList.add(rowObject);
        }
        return typeRowList;
    }
}
