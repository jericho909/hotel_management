package view;

import javax.swing.*;
import java.awt.*;

public class Layout extends JFrame {

    public void layoutStart(int height, int width){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Hotel Management");
        //the dimensions of the GUI
        this.setSize(width,height);
        //calculating the viewport to be in the middle of the users screen
        int userViewportHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getSize().height) / 2;
        int userViewportWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getSize().width) / 2;
        this.setLocation(userViewportWidth, userViewportHeight);
        this.setVisible(true);
    }
}
