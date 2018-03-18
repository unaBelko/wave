package com.example.armin.wave.login;

/**
 * Created by Una on 27.10.2017..
 */

public interface ILoginContract {

    interface Presenter{
        boolean Login(String username,String password);
        void CheckLogin(String session);
    }

    interface View{
        void OnLogin(boolean success);
        void OnLoginChecked(boolean success);
    }

}
