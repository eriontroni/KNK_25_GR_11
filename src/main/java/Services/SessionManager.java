package Services;

import Models.Users;

public class SessionManager {
    private static SessionManager instance;
    //    Anetaret e Sessionit
    private Users user;

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(Users user){
        this.user = user;
    }

    public Users getCurrentUser(){
        return this.user;
    }
}