package com.example.armin.wave.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.LoginRegisterService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 27.10.2017..
 */

public class LoginPresenter implements ILoginContract.Presenter {

    ILoginContract.View mView;
    LoginRegisterService service;
    Context context;

    public LoginPresenter(ILoginContract.View view, Context context) {
        mView = view;
        service = new LoginRegisterService();
        this.context = context;
    }

    @Override
    public boolean Login(String username, String password) {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetLoginAPI().postLoginSession(username, password, "PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String json = response.body().string();
                    Log.i("response", json);
                    JSONObject object = new JSONObject(json);
                    LoginResponse(object.getBoolean("success"), json);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("FAILURE", t.getLocalizedMessage());
            }
        });
        return true;
    }

    @Override
    public void CheckLogin(final String session) {
        if (session.equals("")) {
            service.GetLoginAPI().aboutUserSession().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String responseJson = null;
                    try {
                        responseJson = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String sessionID = null;
                    try {
                        JSONObject object = new JSONObject(responseJson);
                        sessionID = object.getString("sessionID");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("RRRRR", responseJson);
                    SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("sessionID", sessionID);
                    editor.apply();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } else {

            service.GetLoginAPI().aboutUser("PHPSESSID=" + session).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    JSONObject object = null;
                    try {
                        String responseJson = response.body().string();
                        object = new JSONObject(responseJson);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    boolean success = false;
                    if (object == null) {
                        Log.i("null", "ja sam null");
                    }
                    try {
                        success = object.getBoolean("success");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Handler handler = new Handler();
                    final boolean finalSuccess = success;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalSuccess) {
                                mView.OnLoginChecked(true);
                            } else {

                                mView.OnLoginChecked(false);
                            }
                        }
                    }, 1000);

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }

    void LoginResponse(boolean success, String json) {
        if (success) {
            Log.i("response", "usao");
            String sessionID = null;
            try {
                JSONObject object = new JSONObject(json);
                sessionID = object.getString("sessionID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("sessionID ", sessionID);
            SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("sessionID", sessionID);
            editor.apply();
            mView.OnLogin(success);
        } else {
            Log.i("response", "usao");

        }
    }

}