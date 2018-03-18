package com.example.armin.wave.user;

import android.content.Context;

/**
 * Created by Una on 29.10.2017..
 */

public class UserPresenter implements IUserContract.Presenter {
    IUserContract.View view;
    Context context;

    public UserPresenter(IUserContract.View view,Context context){
        this.view = view;
        this.context = context;
    }
}
