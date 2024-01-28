package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JComboBox cmb_roles;
    private JButton btn_search;
    private JButton btn_clear;
    private JLabel lbl_search;
    private DefaultTableModel default_tbl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private UserManager userManager;
    Object[] columnsOfUserTable;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        layoutStart(500,500);

        if (user == null){
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUser_name() + ".");

        initializeUserTable(null);
        initializeUserMenuOptions();

        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

    }

    private void initializeUserTable(ArrayList<Object[]> userList){
        this.columnsOfUserTable = new Object[]{"User ID", "User Name", "User Role"};
        if (userList == null){
            userList = this.userManager.getForTable(columnsOfUserTable.length, this.userManager.fetchAllUsers());
        }
        this.createTable(this.default_tbl_user, this.tbl_user, columnsOfUserTable, userList);
    }

    private void initializeUserMenuOptions(){
        tableRowSelect(this.tbl_user);

        this.user_menu = new JPopupMenu();

        this.user_menu.add("Add New User").addActionListener(e -> {
            UserAddEditMenu userAddEditMenu = new UserAddEditMenu(null);
            userAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeUserTable(null);
                    initializeUserMenuOptions();
                }
            });
        });

        this.user_menu.add("Edit User").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            UserAddEditMenu userAddEditMenu = new UserAddEditMenu(this.userManager.getById(selectedUserId));
            userAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeUserTable(null);
                    initializeUserMenuOptions();
                }
            });
        });

        this.user_menu.add("Delete User").addActionListener(e -> {
            if (Helper.confirm("Yes")){
                int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.deleteUser(selectedUserId)){
                    Helper.showCustomMessage("User deleted.", "Operation successful.");
                    initializeUserTable(null);
                    initializeUserMenuOptions();
                } else {
                    Helper.showCustomMessage("User cancelled operation.", "Aborted by user.");
                }
            }
        });
        this.tbl_user.setComponentPopupMenu(user_menu);

        this.btn_search.addActionListener(e -> {
            String selectedRole = this.cmb_roles.getSelectedItem().toString().toLowerCase();
            ArrayList<User> userListSearch = this.userManager.searchUsersByRole(selectedRole);

            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.columnsOfUserTable.length, userListSearch);
            initializeUserTable(userRowListBySearch);

        });

        this.btn_clear.addActionListener(e -> {
            initializeUserTable(null);
            this.cmb_roles.setSelectedItem(null);
        });
    }

}
