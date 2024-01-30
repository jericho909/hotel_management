package view;

import business.HotelManager;
import business.TypeManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;

import javax.swing.*;

public class TypeAddMenu extends Layout {

    private JTextField fld_hotel_type_name;
    private JComboBox<ComboItem> cmb_hotelnames;
    private JLabel lbl_hoteltype;
    private JLabel lbl_hoteltypename;
    private JButton btn_save;
    private JPanel container;
    private JComboBox cmb_types;
    private TypeManager typeManager;
    private HotelManager hotelManager;

    public TypeAddMenu() {

        this.typeManager = new TypeManager();
        this.hotelManager = new HotelManager();
        this.add(container);

        this.layoutStart(500,500);

        //populate the hotel selection boxes
        for (Hotel hotel: this.hotelManager.fetchAllHotels()){
            this.cmb_hotelnames.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
        }
        btn_save.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_hotelnames.getSelectedItem();
            result = this.typeManager.saveType(new entities.Type(selectedHotel.getKey(), cmb_types.getSelectedItem().toString()));
            Helper.showCustomMessage("Added new type.", "Operation successful.");
            dispose();
        });
    }
}
