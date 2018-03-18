package com.example.armin.wave.matchBet;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.UtakmiceService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 28.10.2017..
 */

public class MatchBetPresenter implements IMatchBetContract.Presenter {
    UtakmiceService service;
    Context context;
    IMatchBetContract.View view;

    public MatchBetPresenter(Context context, IMatchBetContract.View view) {
        this.context = context;
        this.view = view;
        service = new UtakmiceService();
    }

    public void AddMatch(String match_id,String fullTimeResult,String selector) {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().getOrCheckTicket("PHPSESSID=" + sessionID,match_id,fullTimeResult,selector).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject object = null;
                boolean success = false;
                String message = "";
                try {
                    object = new JSONObject(response.body().string());
                    success= object.getBoolean("success");
                    if (!success){
                        message = object.getString("error");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("response",object.toString());
                view.OnMatchAdded(success,message);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("failure","failures");
            }
        });
    }
}
