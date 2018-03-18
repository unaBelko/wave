package com.example.armin.wave.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.armin.wave.R;
import com.example.armin.wave.standings.StandingsActivity;
import com.example.armin.wave.utakmice.IUtakmiceContract;
import com.example.armin.wave.utakmice.model.GenericList;
import com.example.armin.wave.utakmice.model.Match;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.GET;

/**
 * Created by Una on 28.10.2017..
 */

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<GenericList> matches;
    Context context;
    IUtakmiceContract.View mView;
    boolean info;

    public ScheduleAdapter(List<GenericList> shcedule, Context context, IUtakmiceContract.View view, boolean info) {
        matches = shcedule;
        this.context = context;
        mView = view;
        this.info = info;
    }

    @Override
    public int getItemViewType(int position) {
        if (matches.get(position) instanceof Match) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(context).inflate(R.layout.match_row_sample, parent, false);
            return new GameViewHolder(v);
        } else {
            View v1 = LayoutInflater.from(context).inflate(R.layout.country_row_sample, parent, false);
            return new CountryViewHolder(v1);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                int characters = 20;
                String homeTeam = matches.get(position).GetName().get("home_team");
                String awayTeam = matches.get(position).GetName().get("away_team");

                if (homeTeam.length() >= 16) {
                    homeTeam = homeTeam.substring(0, 16);
                    homeTeam += "...";
                }
                if (awayTeam.length() >= 16) {
                    awayTeam = awayTeam.substring(0, 16);
                    awayTeam += "...";
                }


                ((GameViewHolder) holder).mHome.setText(homeTeam);
                ((GameViewHolder) holder).mAway.setText(awayTeam);
                if (info) {
                    ((GameViewHolder) holder).mTime.setText(matches.get(position).GetName().get("time"));
                } else {
                    ((GameViewHolder) holder).mTime.setText(matches.get(position).GetName().get("home_score") + ":" + matches.get(position).GetName().get("away_score"));
                }

                ((GameViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mView != null) {
                            mView.OnClick(position);
                        }

                    }
                });
                break;
            case 1:
                String country = matches.get(position).GetName().get("country_name");
                final String tournamentId = matches.get(position).GetName().get("tournament_id");

                if (country.length() >= 25) {
                    country = country.substring(0, 25);
                    country += "...";
                }
                ((CountryViewHolder) holder).mCoutry.setText(country);
                ((CountryViewHolder) holder).mStandings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, StandingsActivity.class);
                        intent.putExtra("tournament_id", tournamentId);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    public void Clear() {
        matches.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_country_name)
        TextView mCoutry;
        @BindView(R.id.tv_standings)
        TextView mStandings;

        public CountryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home)
        TextView mHome;
        @BindView(R.id.tv_away)
        TextView mAway;
        @BindView(R.id.tv_time)
        TextView mTime;
        @BindView(R.id.ll_layout)
        LinearLayout mLayout;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
