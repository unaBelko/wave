package com.example.armin.wave.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.armin.wave.R;
import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 27.10.2017..
 */

public class RegisterActivity extends AppCompatActivity implements IRegisterContract.View {
    @BindView(R.id.tv_firstName)
    EditText mFirstName;
    @BindView(R.id.tv_lastName)
    EditText mLastName;
    @BindView(R.id.tv_username)
    EditText mUsername;
    @BindView(R.id.tv_email)
    EditText mEmail;
    @BindView(R.id.tv_password)
    EditText mPassword;
    @BindView(R.id.btn_register)
    Button mRegister;
    IRegisterContract.Presenter mPresenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);

        mPresenter = new RegisterPresenter(this,this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mPresenter.Register(mFirstName.getText().toString(),mLastName.getText().toString(),mUsername.getText().toString(),mEmail.getText().toString(),mPassword.getText().toString());
               // mPresenter.Register("armin","humic","armaa","arma@arma.com","neskipass");
            }
        });
    }

    @Override
    public void OnRegister(boolean success, String messsage) {
        if(success){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,messsage,Toast.LENGTH_SHORT).show();
        }
    }
}
