package com.example.armin.wave.daily_results;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Una on 28.10.2017..
 */

public interface IDailyResultsContract {

    interface Presenter{
        void LoadMatches();
    }

    interface View{
        void OnMatchesLoad(String json);
    }

}
