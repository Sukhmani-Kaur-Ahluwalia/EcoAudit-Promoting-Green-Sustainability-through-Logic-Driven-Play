// this is the main class, it just runs the program
// i start with loggedIn = false so the login form shows up first

public class Main {
    public static void main(String[] args) {

        // not logged in at the start
        boolean loggedIn = false;

        System.out.println("starting app...");

        // open the welcome screen
        System.out.println("Logging In");
        WelcomePage wp = new WelcomePage(loggedIn);
    }
}
