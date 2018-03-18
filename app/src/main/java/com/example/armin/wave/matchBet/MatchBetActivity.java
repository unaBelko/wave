package com.example.armin.wave.matchBet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.armin.wave.R;
import com.example.armin.wave.utakmice.UtakmiceActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Una on 28.10.2017..
 */

public class MatchBetActivity extends AppCompatActivity implements IMatchBetContract.View {
    @BindView(R.id.tv_winner_1)
    TextView mWinner1;
    @BindView(R.id.tv_winner_x)
    TextView mWinnerx;
    @BindView(R.id.tv_winner_2)
    TextView mWinner2;
    @BindView(R.id.tv_goal_minus)
    TextView mGoalMinus;
    @BindView(R.id.tv_goal_plus)
    TextView mGoalPlus;
    @BindView(R.id.tv_team1)
    TextView mTeam1;
    @BindView(R.id.tv_team2)
    TextView mTeam2;
    @BindView(R.id.btn_bet)
    Button mBet;
    @BindView(R.id.tv_home_odds)
    TextView mHomeOdds;
    @BindView(R.id.tv_draw_odds)
    TextView mDrawOdds;
    @BindView(R.id.tv_away_odds)
    TextView mAwayOdds;
    @BindView(R.id.tv_time_match)
    TextView mTime;

    String matchId;
    List<HashMap<Integer, Boolean>> buttons;
    int[] ids = {R.id.tv_winner_x, R.id.tv_winner_1, R.id.tv_winner_2, R.id.tv_goal_plus, R.id.tv_goal_minus};
    Context context;
    IMatchBetContract.Presenter presenter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_bet_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String homeTeam = intent.getStringExtra("home_team");
        String awayTeam = intent.getStringExtra("away_team");
        matchId = intent.getStringExtra("match_id");
        String homeOdds = intent.getStringExtra("home_odds");
        String awayOdds = intent.getStringExtra("away_odds");
        String drawOdds = intent.getStringExtra("draw_odds");
        String time = intent.getStringExtra("time");

        if (homeTeam.length() >= 15) {
            homeTeam = homeTeam.substring(0, 15);
            homeTeam += "...";
        }
        if (awayTeam.length() >= 15) {
            awayTeam = awayTeam.substring(0, 15);
            awayTeam += "...";
        }

        presenter = new MatchBetPresenter(this, this);
        context = this;

        buttons = new ArrayList<>();
        setAllToFalse();

        mWinner1.setOnClickListener(winnerListener);
        mWinnerx.setOnClickListener(winnerListener);
        mWinner2.setOnClickListener(winnerListener);
        mGoalMinus.setOnClickListener(goalListener);
        mGoalPlus.setOnClickListener(goalListener);

        mTeam1.setText(homeTeam);
        mTeam2.setText(awayTeam);
        mTime.setText(time);
        mHomeOdds.setText(homeOdds);
        mDrawOdds.setText(drawOdds);
        mAwayOdds.setText(awayOdds);
        final String[] fullTimeResult = {"NONE"};
        final String[] selector = {"NONE"};

        mBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).get(ids[i])) {
                        if (i <= 2) {
                            fullTimeResult[0] = String.valueOf(i);
                            break;
                        } else {
                            if (ids[i] == R.id.tv_goal_minus) {
                                selector[0] = "0";
                            } else {
                                selector[0] = "1";
                            }
                            break;
                        }
                    }
                }
                presenter.AddMatch(matchId, fullTimeResult[0], selector[0]);
            }
        });

    }

    View.OnClickListener winnerListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            setAllToFalse();
            switch (v.getId()) {
                case R.id.tv_winner_1:
                    buttons.get(1).put(ids[1], true);
                    mWinner1.setBackground(context.getDrawable(R.color.lightGreen));
                    break;
                case R.id.tv_winner_x:
                    buttons.get(0).put(ids[0], true);
                    mWinnerx.setBackground(context.getDrawable(R.color.lightGreen));
                    break;
                case R.id.tv_winner_2:
                    buttons.get(2).put(ids[2], true);
                    mWinner2.setBackground(context.getDrawable(R.color.lightGreen));
                    break;
            }
        }
    };

    View.OnClickListener goalListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            setAllToFalse();
            switch (v.getId()) {
                case R.id.tv_goal_plus:
                    buttons.get(3).put(ids[3], true);
                    mGoalPlus.setBackground(context.getDrawable(R.color.lightGreen));
                    break;
                case R.id.tv_goal_minus:
                    buttons.get(4).put(ids[4], true);
                    mGoalMinus.setBackground(context.getDrawable(R.color.lightGreen));
                    break;
            }
        }
    };

    @SuppressLint("UseSparseArrays")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setAllToFalse() {
        if (buttons.size() == 0) {
            for (int i = 0; i < 5; i++) {
                HashMap<Integer, Boolean> map = new HashMap<>();
                map.put(ids[i], false);
                buttons.add(map);
            }
        } else {
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).put(ids[i], false);
            }
        }
        mGoalMinus.setBackground(context.getDrawable(R.color.lightGreen30Opacity));
        mGoalPlus.setBackground(context.getDrawable(R.color.lightGreen30Opacity));
        mWinner1.setBackground(context.getDrawable(R.color.lightGreen30Opacity));
        mWinnerx.setBackground(context.getDrawable(R.color.lightGreen30Opacity));
        mWinner2.setBackground(context.getDrawable(R.color.lightGreen30Opacity));

    }

    @Override
    public void OnMatchAdded(boolean success, String message) {
        if (success) {
            Toast.makeText(this, "Bet added", Toast.LENGTH_LONG).show();
            onBackPressed();

        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
