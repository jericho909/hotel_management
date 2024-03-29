package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;
import entities.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonAddMenu extends Layout {
    private JComboBox cmb_hotelnames;
    private JButton btn_save;
    private JPanel container;
    private JLabel lbl_hotel;
    private JLabel lbl_seasonstart;
    private JLabel lbl_seasonend;
    private JFormattedTextField fmt_fld_seasonstart;
    private JFormattedTextField fmt_fld_seasonend;
    private JTextField fld_seasonName;
    private JTextField fld_seasonRate;
    private JLabel lbl_seasonname;
    private JLabel lbl_seasonRate;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;

    public SeasonAddMenu(){
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        this.layoutStart(750, 750);


        //populate hotel names
        for (Hotel hotel: this.hotelManager.fetchAllHotels()){
            this.cmb_hotelnames.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
        }

        btn_save.addActionListener(e -> {
            if (Helper.emptyFieldChecker(new JTextField[]{fld_seasonName, fld_seasonRate, fmt_fld_seasonend, fmt_fld_seasonstart})){ //check for empty fields
                Helper.showErrorMessage("Please fill all the fields.");
            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmb_hotelnames.getSelectedItem();
                result = this.seasonManager.saveSeason(new Season(selectedHotel.getKey(),
                        LocalDate.parse(fmt_fld_seasonstart.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalDate.parse(fmt_fld_seasonend.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        fld_seasonName.getText(), Double.parseDouble(fld_seasonRate.getText())));

            }
            Helper.showCustomMessage("Added new season.", "Operation successful.");
            dispose();
        });
    }


    //for formatting the date fields
    private void createUIComponents() throws ParseException {
        this.fmt_fld_seasonstart = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_fld_seasonstart.setText("01/01/2024");
        this.fmt_fld_seasonend = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_fld_seasonend.setText("02/01/2024");
    }
}