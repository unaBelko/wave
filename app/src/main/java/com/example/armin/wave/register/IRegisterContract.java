package com.example.armin.wave.register;

/**
 * Created by Una on 27.10.2017..
 */

public interface IRegisterContract {
    interface Presenter{
        boolean Register(String firstName,String lastName,String username,String email,String password);
    }

    interface View{
        void OnRegister(boolean success,String messsage);
    }
}
