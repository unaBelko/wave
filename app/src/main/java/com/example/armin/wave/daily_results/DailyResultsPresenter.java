package com.example.armin.wave.daily_results;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.UtakmiceService;
import com.example.armin.wave.utakmice.model.UtakmiceResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 28.10.2017..
 */

public class DailyResultsPresenter implements IDailyResultsContract.Presenter {
    UtakmiceService service;
    Context context;
    IDailyResultsContract.View view;

    public DailyResultsPresenter(Context context,IDailyResultsContract.View view){
        service = new UtakmiceService();
        this.context = context;
        this.view = view;
    }

    @Override
    public void LoadMatches() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().getMatchResults("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = null;
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("logloglog",json);
                view.OnMatchesLoad(json);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("failure",call.request().url().toString());
            }
        });

    }
}
