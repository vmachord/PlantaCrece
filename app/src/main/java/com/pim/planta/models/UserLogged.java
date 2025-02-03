package com.pim.planta.models;

public class UserLogged {

    private static UserLogged instance;
    private User currentUser;
    private long totalTimeUse;

    public static synchronized UserLogged getInstance() {
        if (instance == null) {
            instance = new UserLogged();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
