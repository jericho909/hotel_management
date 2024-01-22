package view;

import business.UserManager;
import core.Helper;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {

    private User user;
    private JPanel container;
    private JPanel pnl_header;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JPanel pnl_main;
    private JTable tbl_user;
    private DefaultTableModel default_tbl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private UserManager userManager;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        layoutStart(500,500);

        if (user == null){
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUser_name() + ".");

        loadUserTable();
        loadMenuOptions();
    }

    private void loadUserTable(){
        Object[] columnsOfUserTable = {"User ID", "User Name", "User Role"};
        ArrayList<Object[]> userList = this.userManager.getForTable(columnsOfUserTable.length);
        this.createTable(this.default_tbl_user, this.tbl_user, columnsOfUserTable, userList);
    }

    private void loadMenuOptions(){
        tableRowSelect(this.tbl_user);

        this.user_menu = new JPopupMenu();

        this.user_menu.add("Add New User").addActionListener(e -> {
            UserAddEditMenu userAddEditMenu = new UserAddEditMenu(null);
            userAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                    loadMenuOptions();
                }
            });
        });

        this.user_menu.add("Edit User").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            System.out.println(selectedUserId);
            System.out.println(this.userManager.getById(selectedUserId).toString());
            UserAddEditMenu userAddEditMenu = new UserAddEditMenu(this.userManager.getById(selectedUserId));

            userAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                    loadMenuOptions();
                }
            });
        });

        this.user_menu.add("Delete User").addActionListener(e -> {
            if (Helper.confirm("Yes")){
                int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
                System.out.println(selectedUserId);
                if (this.userManager.deleteUser(selectedUserId)){
                    Helper.showCustomMessage("User deleted.", "Operation successful.");
                    loadUserTable();
                    loadMenuOptions();
                } else {
                    Helper.showCustomMessage("User cancelled operation.", "Aborted by user.");
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(user_menu);
    }
}
