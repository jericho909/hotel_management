import business.UserManager;
import core.Helper;
import view.AdminView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        //change the theme to nimbus
        Helper.setTheme();
        //instantiate user login page
        LoginView loginView = new LoginView();
    }
}