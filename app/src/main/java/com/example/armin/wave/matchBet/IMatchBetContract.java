package com.example.armin.wave.matchBet;

/**
 * Created by Una on 28.10.2017..
 */

public interface IMatchBetContract {

    interface Presenter{
        void AddMatch(String match_id,String fullTimeResult,String selector);
    }

    interface View{
        void OnMatchAdded(boolean success,String message);
    }

}
