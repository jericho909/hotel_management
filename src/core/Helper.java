package core;

import business.ReservationManager;
import entities.Reservation;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Helper {

    public static void setTheme(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if ("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

    }

    public static void showErrorMessage(String str){
        JOptionPane.showMessageDialog(null, str, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showCustomMessage(String str, String title){
        JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean emptyFieldChecker(JTextField[] fieldList){
        for (JTextField textField: fieldList){
            if (isFieldEmpty(textField)){
                return true;
            }
        }

        return false;
    }

    public static boolean emptyCheckboxChecker(JCheckBox[] checkBoxes){
        for (JCheckBox checkBox: checkBoxes){
            if (!checkBox.isSelected()){
                return true;
            }
        }
        return false;
    }

    public static boolean confirm(String str){
        String msg;
        if (str.equals("Yes")){
            msg = "Please confirm deletion.";
        } else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg,"Are you sure?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static int calculateGuestNumber(JTextField[] fieldList){
        int sum = 0;
        if (Helper.emptyFieldChecker(fieldList)){
            return -1;
        }
        for (JTextField field:fieldList){
            sum += Integer.parseInt(field.getText());
        }

        return sum;
    }

    public static boolean checkDateAvailability(LocalDate startDate, LocalDate endDate, ArrayList<Reservation> reservationArrayList, int roomId) {

        for (Reservation reservation : reservationArrayList) {
            System.out.println(reservation.getRoom_id());
            System.out.println(roomId);
            if (reservation.getRoom_id() == roomId){
                if ((startDate.isAfter(reservation.getReservation_start_date()) && startDate.isBefore(reservation.getReservation_end_date())) ||
                        (endDate.isAfter(reservation.getReservation_start_date()) && endDate.isBefore(reservation.getReservation_end_date())) ||
                        (startDate.isBefore(reservation.getReservation_start_date()) && endDate.isAfter(reservation.getReservation_end_date()))) {
                    return false; // Overlapping reservation found
                }
            }
        }
        return true; // No overlapping reservation found
    }

}
