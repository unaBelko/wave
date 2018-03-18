package com.example.armin.wave.utakmice;

import com.example.armin.wave.utakmice.model.Match;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public interface IUtakmiceContract {

    interface Presenter{
        void LoadMatches();
    }

    interface View{
        void OnMatchesLoad(String json);
        void OnClick(int position);
    }

}
