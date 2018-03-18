package com.example.armin.wave.utakmice;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.UtakmiceService;
import com.example.armin.wave.utakmice.model.Match;
import com.example.armin.wave.utakmice.model.UtakmiceResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 28.10.2017..
 */

public class UtakmicePresenter implements IUtakmiceContract.Presenter {
    IUtakmiceContract.View mView;
    UtakmiceService service;
    Context context;

    public UtakmicePresenter(IUtakmiceContract.View view, Context context) {
        mView = view;
        service = new UtakmiceService();
        this.context = context;
    }


    @Override
    public void LoadMatches() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().getMatchesJson("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = null;
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mView.OnMatchesLoad(json);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}



