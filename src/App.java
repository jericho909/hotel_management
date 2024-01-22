import business.UserManager;
import core.Helper;
import view.AdminView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        LoginView loginView = new LoginView();
        //UserManager userManager = new UserManager();
        //AdminView adminView = new AdminView(userManager.fetchUserWithLoginInfo("admin", "1234"));
    }
}