package com.example.armin.wave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armin.wave.R;
import com.example.armin.wave.standings.IStandingsContract;
import com.example.armin.wave.standings.model.IStandingsGeneric;
import com.example.armin.wave.standings.model.StandingsResponse;
import com.example.armin.wave.standings.model.TeamStandings;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 28.10.2017..
 */

public class StandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<IStandingsGeneric> responses;
    List<StandingsResponse> responseRaw;
    Context context;
    IStandingsContract.View mView;
    int t = 0;

    public StandingsAdapter(List<IStandingsGeneric> response, Context context, IStandingsContract.View view, List<StandingsResponse> responseRaw) {
        responses = response;
        this.context = context;
        mView = view;
        this.responseRaw = responseRaw;
    }

    @Override
    public int getItemViewType(int position) {
        if (responseRaw.size() <= 1) {
            Log.i("koliko", "jedno");

            return position == 0 ? 0 : 1;
        } else {
            if(responses.get(position) instanceof StandingsResponse){
                return 0;
            }
            return 1;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(context).inflate(R.layout.standings_header, parent, false);
            return new HeaderViewHolder(v);
        } else {
            View v1 = LayoutInflater.from(context).inflate(R.layout.standings_row, parent, false);
            return new RowViewHolder(v1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                if (responseRaw.size() <= 1) {
                    ((HeaderViewHolder)holder).mGroup.setText("");
                } else {
                    ((HeaderViewHolder)holder).mGroup.setText("Group: "+responses.get(position).GetGet());
                }

                break;
            case 1:
                if (responseRaw.size() <= 1) {

                    ((RowViewHolder) holder).mNumber.setText(responses.get(position).GetRank());
                    ((RowViewHolder) holder).mClub.setText(responses.get(position).GetTeamName());
                    ((RowViewHolder) holder).mPoints.setText(responses.get(position).GetPoints());
                } else {
                    ((RowViewHolder) holder).mNumber.setText(responses.get(position).GetRank());
                    ((RowViewHolder) holder).mClub.setText(responses.get(position).GetTeamName());
                    ((RowViewHolder) holder).mPoints.setText(responses.get(position).GetPoints());
                }

                break;
        }
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_group)
        TextView mGroup;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_numberInt)
        TextView mNumber;
        @BindView(R.id.tv_clubString)
        TextView mClub;
        @BindView(R.id.tv_pointsInt)
        TextView mPoints;

        public RowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
