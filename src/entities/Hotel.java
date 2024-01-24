package entities;

public class Hotel {
    private int id;
    private String hotel_name;
    private String hotel_city;
    private String hotel_adress;
    private String hotel_email;
    private String hotel_phone;
    private String hotel_star;
    private boolean hotel_freeparking;
    private boolean hotel_freewifi;
    private boolean hotel_pool;
    private boolean hotel_gym;
    private boolean hotel_concierge;
    private boolean hotel_spa;
    private boolean hotel_roomservice;

    public Hotel(){

    }

    public Hotel(String hotel_name, String hotel_city, String hotel_adress, String hotel_email, String hotel_phone, String hotel_star, boolean hotel_freeparking, boolean hotel_freewifi, boolean hotel_pool, boolean hotel_gym, boolean hotel_concierge, boolean hotel_spa, boolean hotel_roomservice) {
        this.hotel_name = hotel_name;
        this.hotel_city = hotel_city;
        this.hotel_adress = hotel_adress;
        this.hotel_email = hotel_email;
        this.hotel_phone = hotel_phone;
        this.hotel_star = hotel_star;
        this.hotel_freeparking = hotel_freeparking;
        this.hotel_freewifi = hotel_freewifi;
        this.hotel_pool = hotel_pool;
        this.hotel_gym = hotel_gym;
        this.hotel_concierge = hotel_concierge;
        this.hotel_spa = hotel_spa;
        this.hotel_roomservice = hotel_roomservice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_adress() {
        return hotel_adress;
    }

    public void setHotel_adress(String hotel_adress) {
        this.hotel_adress = hotel_adress;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_star() {
        return hotel_star;
    }

    public void setHotel_star(String hotel_star) {
        this.hotel_star = hotel_star;
    }

    public boolean isHotel_freeparking() {
        return hotel_freeparking;
    }

    public void setHotel_freeparking(boolean hotel_freeparking) {
        this.hotel_freeparking = hotel_freeparking;
    }

    public boolean isHotel_freewifi() {
        return hotel_freewifi;
    }

    public void setHotel_freewifi(boolean hotel_freewifi) {
        this.hotel_freewifi = hotel_freewifi;
    }

    public boolean isHotel_pool() {
        return hotel_pool;
    }

    public void setHotel_pool(boolean hotel_pool) {
        this.hotel_pool = hotel_pool;
    }

    public boolean isHotel_gym() {
        return hotel_gym;
    }

    public void setHotel_gym(boolean hotel_gym) {
        this.hotel_gym = hotel_gym;
    }

    public boolean isHotel_concierge() {
        return hotel_concierge;
    }

    public void setHotel_concierge(boolean hotel_concierge) {
        this.hotel_concierge = hotel_concierge;
    }

    public boolean isHotel_spa() {
        return hotel_spa;
    }

    public void setHotel_spa(boolean hotel_spa) {
        this.hotel_spa = hotel_spa;
    }

    public boolean isHotel_roomservice() {
        return hotel_roomservice;
    }

    public void setHotel_roomservice(boolean hotel_roomservice) {
        this.hotel_roomservice = hotel_roomservice;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotel_name='" + hotel_name + '\'' +
                ", hotel_city='" + hotel_city + '\'' +
                ", hotel_adress='" + hotel_adress + '\'' +
                ", hotel_email='" + hotel_email + '\'' +
                ", hotel_phone='" + hotel_phone + '\'' +
                ", hotel_star='" + hotel_star + '\'' +
                ", hotel_freeparking=" + hotel_freeparking +
                ", hotel_freewifi=" + hotel_freewifi +
                ", hotel_pool=" + hotel_pool +
                ", hotel_gym=" + hotel_gym +
                ", hotel_concierge=" + hotel_concierge +
                ", hotel_spa=" + hotel_spa +
                ", hotel_roomservice=" + hotel_roomservice +
                '}';
    }
}
