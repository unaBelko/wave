package com.example.armin.wave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.home.HomeActivity;
import com.example.armin.wave.login.ILoginContract;
import com.example.armin.wave.login.LoginPresenter;
import com.example.armin.wave.network.LoginRegisterService;
import com.example.armin.wave.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    @BindView(R.id.et_username)
    EditText mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.tv_createAccount)
    TextView mRegister;
    @BindView(R.id.mainLoginLayout)
    LinearLayout mMainLayout;
    @BindView(R.id.loginProgressBar)
    LinearLayout mProgressLayout;
    @BindView(R.id.pb_LoginLoad)
    ProgressBar mProgressBar;
    ILoginContract.Presenter mPresenter;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = this;
        mPresenter = new LoginPresenter(this, context);

        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(context, "session");
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sessionID","");
        editor.apply();*/
        String sessionID = sharedPreferences.getString("sessionID", "");

        mPresenter.CheckLogin(sessionID);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.Login(mUsername.getText().toString(), mPassword.getText().toString());

            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void OnLogin(boolean success) {
        if (success) {
            Log.i("success", "success");
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {

        }
    }

    @Override
    public void OnLoginChecked(boolean success) {
        Log.i("je li viseee", String.valueOf(success));
        if (success) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            mMainLayout.setVisibility(View.VISIBLE);
            mProgressLayout.setVisibility(View.GONE);
        }
    }


}


