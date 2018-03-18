package com.example.armin.wave.ticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.UtakmiceService;
import com.example.armin.wave.ticket.model.Item;
import com.example.armin.wave.ticket.model.TicketResponse;
import com.example.armin.wave.utakmice.UtakmiceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 29.10.2017..
 */

public class TicektPresenter implements ITicketContract.Preseneter {
    UtakmiceService service;
    Context context;
    ITicketContract.View view;

    public TicektPresenter(Context context,ITicketContract.View view){
        service = new UtakmiceService();
        this.context = context;
        this.view = view;
    }

    @Override
    public void getTickets(String betType) {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().ticketGet( "PHPSESSID=" + sessionID,betType).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                TicketResponse ticketResponse = response.body();
                view.onTicketsFetched(ticketResponse);
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void Submit() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().placeBet("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject object;
                boolean success = false;
                String message = "";
                try{
                    object = new JSONObject(response.body().string());
                    success = object.getBoolean("success");
                    if (success){
                        message = object.getString("error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.onSubmit(success,message);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void Clear() {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().clearBet("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject object;
                boolean success = false;
                String message = "";
                try{
                    object = new JSONObject(response.body().string());
                    success = object.getBoolean("success");
                    if (success){
                        message = object.getString("error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.onClear(success,message);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void Remove(final int position, String matchId, final List<Item>items) {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().clearMatch("PHPSESSID=" + sessionID,matchId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                items.remove(position);
                view.onRemove(items);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
