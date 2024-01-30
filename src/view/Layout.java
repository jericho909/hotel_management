package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {
    //this class is the ancestor of all the view classes, in which all the common methods live
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

    public void createTable(DefaultTableModel defaultTableModel, JTable jTable, Object[] cols, ArrayList<Object[]> rows){
        //we set the column names
        defaultTableModel.setColumnIdentifiers(cols);
        //set the model
        jTable.setModel(defaultTableModel);
        //don't want the user reorganizing the table
        jTable.getTableHeader().setReorderingAllowed(false);
        //for the GUI
        jTable.getColumnModel().getColumn(0).setMaxWidth(75);
        //since we are replacing our generated JTable with the manipulated defaultTable,
        // we need our original table to be invisible
        jTable.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) jTable.getModel();
        clearModel.setRowCount(0);

        if (rows == null){
            rows = new ArrayList<>();
        }

        for (Object[] row: rows){
            defaultTableModel.addRow(row);
        }
    }
    //we get the row by where the user clicked in the screen
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }

    //we use this method for getting the ids of the selected items since all the ids are in index 0
    public int getTableSelectedRow(JTable table, int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());

    }
}
