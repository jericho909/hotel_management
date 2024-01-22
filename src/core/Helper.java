package core;

import javax.swing.*;

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

    public static void showErrorMessage(String str, String title){
        JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
