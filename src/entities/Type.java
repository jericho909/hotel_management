package entities;

public class Type {
    private int id;
    private int hotel_id;
    private String type_hotel_name;

    public Type() {
    }

    public Type(int hotel_id, String type_hotel_name) {
        this.hotel_id = hotel_id;
        this.type_hotel_name = type_hotel_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getType_hotel_name() {
        return type_hotel_name;
    }

    public void setType_hotel_name(String type_hotel_name) {
        this.type_hotel_name = type_hotel_name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", type_hotel_name='" + type_hotel_name + '\'' +
                '}';
    }
}
