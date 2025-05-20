package Services;

public class sessions {
    private static sessions instance = new sessions();
    private Object currentUser;
    private boolean loggedIn = false;


    private sessions() {}

    public static sessions getInstance() {
        return instance;
    }

    public void login(Object user) {
        this.currentUser = user;
        this.loggedIn = true;
    }

    public void logout() {
        this.currentUser = null;
        this.loggedIn = false;
    }

    public Object getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
