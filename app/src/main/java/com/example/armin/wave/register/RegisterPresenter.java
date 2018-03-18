package com.example.armin.wave.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.LoginRegisterService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 27.10.2017..
 */

public class RegisterPresenter implements IRegisterContract.Presenter {

    IRegisterContract.View mView;
    LoginRegisterService service;
    Context context;

    public RegisterPresenter(IRegisterContract.View view,Context context){
        mView = view;
        service = new LoginRegisterService();
        this.context = context;
    }

    @Override
    public boolean Register(String firstName, String lastName, String username, String email, String password) {

        service.GetLoginAPI().postRegister(username,password,email,firstName,lastName).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    IsOk(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("FAILURE","fail");
            }
        });
        return true;
    }

    void IsOk(String json){
        Log.i("RESPONSE REGISTER",json);
        JSONObject object = null;
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            boolean success = object.getBoolean("success");
            String message = null;
            if(success){
                String session = object.getString("sessionID");
                SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context,"session");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sessionID",session);
                editor.apply();
                Log.i("session id register",session);
                mView.OnRegister(success,null);
            }else{
                JSONObject messageObject = object.getJSONObject("error_fields");
                Iterator<String> keys = messageObject.keys();
                while (keys.hasNext()){
                    String key = keys.next();
                    String value = messageObject.getString(key);
                    mView.OnRegister(success,value);
                }

               // mView.OnRegister(object.getBoolean("success"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}