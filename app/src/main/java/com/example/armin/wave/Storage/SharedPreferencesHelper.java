package com.example.armin.wave.Storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Una on 27.10.2017..
 */

public class SharedPreferencesHelper {
    public static SharedPreferences GetSharedPreferences(Context context,String name){
        return context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }
}
