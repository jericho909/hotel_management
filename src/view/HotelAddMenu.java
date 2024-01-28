package view;

import business.HotelManager;
import core.Helper;
import entities.Hotel;

import javax.swing.*;
import java.awt.*;

public class HotelAddMenu extends Layout{
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_adress;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_star;
    private JCheckBox check_freeparking;
    private JCheckBox check_freewifi;
    private JCheckBox check_pool;
    private JCheckBox check_gym;
    private JCheckBox check_concierge;
    private JCheckBox check_spa;
    private JCheckBox check_roomservice;
    private JButton btn_save;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hotel_city;
    private JLabel lbl_hotel_adress;
    private JLabel lbl_hotel_email;
    private JLabel lbl_hotel_phone;
    private JLabel lbl_hotel_star;
    private JLabel lbl_freeparking;
    private JLabel lbl_freewifi;
    private JLabel lbl_pool;
    private JLabel lbl_gym;
    private JLabel lbl_concierge;
    private JLabel lbl_spa;
    private JLabel lbl_roomservice;
    private JComboBox cmb_hotelStar;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelAddMenu() {

        this.hotelManager = new HotelManager();

        this.add(container);

        this.layoutStart((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 200, 1000);

        btn_save.addActionListener(e -> {
            JTextField[] fields = {fld_hotel_name,fld_hotel_city,fld_hotel_adress,fld_hotel_email,fld_hotel_phone};

            boolean result = false;
            if (Helper.emptyFieldChecker(fields)){
                Helper.showErrorMessage("Please fill all the fields.");
            } else {
                result = this.hotelManager.saveHotel(new Hotel(fld_hotel_name.getText(), fld_hotel_city.getText(),
                        fld_hotel_adress.getText(),fld_hotel_email.getText(), fld_hotel_phone.getText(),
                        roomStarRatingToString(cmb_hotelStar.getSelectedItem().toString()), check_freeparking.isSelected(), check_freewifi.isSelected(),
                        check_pool.isSelected(), check_gym.isSelected(), check_concierge.isSelected(),
                        check_spa.isSelected(), check_roomservice.isSelected()));
                result = true;
            }

            if (result){
                Helper.showCustomMessage("Added hotel.", "Operation successful.");
                dispose();
            }
        });
    }
    private String roomStarRatingToString(String rating){
        return switch (rating) {
            case "*" -> "1";
            case "**" -> "2";
            case "***" -> "3";
            case "****" -> "4";
            case "*****" -> "5";
            default -> "Invalid";
        };
    }
}
