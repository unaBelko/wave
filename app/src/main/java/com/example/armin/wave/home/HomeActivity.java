package com.example.armin.wave.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.armin.wave.LoginActivity;
import com.example.armin.wave.R;
import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.TicketHistory.TicketHistory;
import com.example.armin.wave.daily_results.DailyResultsActivity;
import com.example.armin.wave.network.LoginRegisterService;
import com.example.armin.wave.ticket.TicketActivity;
import com.example.armin.wave.user.UserActivity;
import com.example.armin.wave.utakmice.UtakmiceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 27.10.2017..
 */

public class HomeActivity extends AppCompatActivity implements IHomeContract {

    @BindView(R.id.iv_historijaListica)
    ImageView mHistorijaListica;
    @BindView(R.id.iv_listicAktivan)
    ImageView mListicAktivan;
    @BindView(R.id.iv_logout)
    ImageView mLogout;
    @BindView(R.id.iv_profil)
    ImageView mProfil;
    @BindView(R.id.iv_results)
    ImageView mResults;
    @BindView(R.id.iv_help)
    ImageView mHelp;
    @BindView(R.id.iv_utakmice)
    ImageView mUtakmice;
    @BindView(R.id.tv_userNameTop)
    TextView mtxt;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        context = this;

        mHistorijaListica.setOnClickListener(onClickListener);
        mListicAktivan.setOnClickListener(onClickListener);
        mLogout.setOnClickListener(onClickListener);
        mProfil.setOnClickListener(onClickListener);
        mResults.setOnClickListener(onClickListener);
        mHelp.setOnClickListener(onClickListener);
        mUtakmice.setOnClickListener(onClickListener);

        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        LoginRegisterService service = new LoginRegisterService();
        service.GetLoginAPI().aboutUser("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONObject object;
                JSONObject user;
                try {
                    object = new JSONObject( response.body().string());
                    user = new JSONObject(object.getString("user"));
                    setUsername(user);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    void setUsername(JSONObject object){
        try {
            mtxt.setText("Welcome, " + object.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void logout(){
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        final String sessionID = sharedPreferences.getString("sessionID", "null");

        LoginRegisterService service = new LoginRegisterService();
        service.GetLoginAPI().logout("PHPSESSID=" + sessionID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sessionID", "");
                editor.apply();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    android.view.View.OnClickListener onClickListener = new android.view.View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            switch (v.getId()) {
                case R.id.iv_utakmice:
                    Intent intent = new Intent(context, UtakmiceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_results:
                    Intent intent1 = new Intent(context, DailyResultsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.iv_listicAktivan:
                    Intent intent2 = new Intent(context, TicketActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.iv_historijaListica:
                    Intent intent3 = new Intent(context, TicketHistory.class);
                    startActivity(intent3);
                    break;
                case R.id.iv_logout:
                    logout();
                    Intent intent4 = new Intent(context, LoginActivity.class);
                    startActivity(intent4);
                    break;
            }
        }
    };

}

