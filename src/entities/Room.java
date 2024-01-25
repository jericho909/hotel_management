package entities;

public class Room {
    private int id;
    private int hotel_id;
    private int room_hotel_type_id;
    private int room_season_id;
    private Double room_price_adult;
    private Double room_price_child;
    private int room_squarefootage;
    private String room_name;
    private int room_bed_count;
    private boolean room_tv;
    private boolean room_minibar;
    private boolean room_console;
    private boolean room_safe;
    private boolean room_projection;
    private int room_stock;

    public Room() {
    }

    public Room(int hotel_id, int room_hotel_type_id, int room_season_id, Double room_price_adult, Double room_price_child, int room_squarefootage, String room_name, int room_bed_count, boolean room_tv, boolean room_minibar, boolean room_console, boolean room_safe, boolean room_projection, int room_stock) {
        this.hotel_id = hotel_id;
        this.room_hotel_type_id = room_hotel_type_id;
        this.room_season_id = room_season_id;
        this.room_price_adult = room_price_adult;
        this.room_price_child = room_price_child;
        this.room_squarefootage = room_squarefootage;
        this.room_name = room_name;
        this.room_bed_count = room_bed_count;
        this.room_tv = room_tv;
        this.room_minibar = room_minibar;
        this.room_console = room_console;
        this.room_safe = room_safe;
        this.room_projection = room_projection;
        this.room_stock = room_stock;
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

    public int getRoom_hotel_type_id() {
        return room_hotel_type_id;
    }

    public void setRoom_hotel_type_id(int room_hotel_type_id) {
        this.room_hotel_type_id = room_hotel_type_id;
    }

    public int getRoom_season_id() {
        return room_season_id;
    }

    public void setRoom_season_id(int room_season_id) {
        this.room_season_id = room_season_id;
    }

    public Double getRoom_price_adult() {
        return room_price_adult;
    }

    public void setRoom_price_adult(Double room_price_adult) {
        this.room_price_adult = room_price_adult;
    }

    public Double getRoom_price_child() {
        return room_price_child;
    }

    public void setRoom_price_child(Double room_price_child) {
        this.room_price_child = room_price_child;
    }

    public int getRoom_squarefootage() {
        return room_squarefootage;
    }

    public void setRoom_squarefootage(int room_squarefootage) {
        this.room_squarefootage = room_squarefootage;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_bed_count() {
        return room_bed_count;
    }

    public void setRoom_bed_count(int room_bed_count) {
        this.room_bed_count = room_bed_count;
    }

    public boolean isRoom_tv() {
        return room_tv;
    }

    public void setRoom_tv(boolean room_tv) {
        this.room_tv = room_tv;
    }

    public boolean isRoom_minibar() {
        return room_minibar;
    }

    public void setRoom_minibar(boolean room_minibar) {
        this.room_minibar = room_minibar;
    }

    public boolean isRoom_console() {
        return room_console;
    }

    public void setRoom_console(boolean room_console) {
        this.room_console = room_console;
    }

    public boolean isRoom_safe() {
        return room_safe;
    }

    public void setRoom_safe(boolean room_safe) {
        this.room_safe = room_safe;
    }

    public boolean isRoom_projection() {
        return room_projection;
    }

    public void setRoom_projection(boolean room_projection) {
        this.room_projection = room_projection;
    }

    public int getRoom_stock() {
        return room_stock;
    }

    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    @Override
    public String  toString() {
        return "Room{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", room_hotel_type_id=" + room_hotel_type_id +
                ", room_season_id=" + room_season_id +
                ", room_price_adult=" + room_price_adult +
                ", room_price_child=" + room_price_child +
                ", room_squarefootage=" + room_squarefootage +
                ", room_name='" + room_name + '\'' +
                ", room_bed_count=" + room_bed_count +
                ", room_tv=" + room_tv +
                ", room_minibar=" + room_minibar +
                ", room_console=" + room_console +
                ", room_safe=" + room_safe +
                ", room_projection=" + room_projection +
                ", room_stock=" + room_stock +
                '}';
    }
}
