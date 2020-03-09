package main.page_objects.interfaces;


import main.infrastructure.Users;


public interface MobileSignIn {
    void signIn(Users.User user);
    void signOut();
}
