package Services;

import Models.Users;

public class UserSessionManager {
    private static UserSessionManager instance;
    //    Anetaret e Sessionit
    private Users user;

    public static UserSessionManager getInstance(){
        if(instance == null){
            instance = new UserSessionManager();
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