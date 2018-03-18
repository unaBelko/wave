package com.example.armin.wave.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.armin.wave.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 29.10.2017..
 */

public class UserActivity extends AppCompatActivity implements IUserContract.View {
    @BindView(R.id.pb_Overall)
    ProgressBar mOverall;
    @BindView(R.id.pb_attack)
    ProgressBar mAttack;

    @BindView(R.id.pb_defense)
    ProgressBar mDefense;

    @BindView(R.id.pb_playmaking)
    ProgressBar mPlaymaking;

    @BindView(R.id.pb_shooting)
    ProgressBar mShooting;

    @BindView(R.id.pb_speed)
    ProgressBar mSpeed;

    @BindView(R.id.txt_attack)
    TextView txtAttack;
    @BindView(R.id.txt_firstname)
    TextView txtFirstname;
    @BindView(R.id.txt_club)
    TextView txtClub;
    @BindView(R.id.txt_position)
    TextView txtPosition;
    @BindView(R.id.txt_overall)
    TextView txtOverall;
    @BindView(R.id.txt_points)
    TextView txtPoints;
    @BindView(R.id.txt_playmaking)
    TextView txtPlaymaking;
    @BindView(R.id.txt_defense)
    TextView txtDefense;
    @BindView(R.id.txt_shooting)
    TextView txtShooting;
    @BindView(R.id.txt_speed)
    TextView txtSpeed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        ButterKnife.bind(this);



    }
}
