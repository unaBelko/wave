package com.example.armin.wave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armin.wave.R;
import com.example.armin.wave.ticket.model.Bet;
import com.example.armin.wave.ticket.model.ITicketGeneric;
import com.example.armin.wave.ticket.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 29.10.2017..
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ITicketGeneric> genericList;

    public HistoryAdapter(Context context, List<ITicketGeneric> genericList) {
        this.context = context;
        this.genericList = genericList;
    }

    @Override
    public int getItemViewType(int position) {
            if (genericList.get(position) instanceof Bet)
                return 0;
            return 1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View v = LayoutInflater.from(context).inflate(R.layout.all_tickets_header, parent, false);
            return new TicketHeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.ticket_row_sample, parent, false);
            return new TicketViewHolder(v);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == 0) {
            ((TicketHeaderViewHolder) holder).mTicketId.setText(genericList.get(position).getId());
            ((TicketHeaderViewHolder) holder).mTotalOdds.setText("Total:"+genericList.get(position).totalOdd());
            switch (Integer.parseInt( genericList.get(position).winState())){
                case 0:
                    ((TicketHeaderViewHolder) holder).mSuccess.setText("PENDING");
                    break;
                case 1:
                    ((TicketHeaderViewHolder) holder).mSuccess.setText("SUCCESS");
                    break;
                case 2:
                    ((TicketHeaderViewHolder) holder).mSuccess.setText("FAILED");
                    break;
            }

        } else {
            String homeTeam = genericList.get(position).GetNesto().get("home");
            String awayTeam =  genericList.get(position).GetNesto().get("away");
            if (homeTeam.length() >= 12) {
                homeTeam = homeTeam.substring(0, 12);
                homeTeam += "...";
            }
            if (awayTeam.length() >= 12) {
                awayTeam = awayTeam.substring(0, 12);
                awayTeam += "...";
            }
            ((TicketViewHolder) holder).mTeam1Ticket.setText(homeTeam);
            ((TicketViewHolder) holder).mTeam2Ticket.setText(awayTeam);
            String type = genericList.get(position).GetNesto().get("result");
            if (type == null) {
                ((TicketViewHolder) holder).mBetType.setText("GOALS");
                if (genericList.get(position).GetNesto().get("morethan2").equals("0"))
                    ((TicketViewHolder) holder).mType.setText("-3");
                if (genericList.get(position).GetNesto().get("morethan2").equals("1"))
                    ((TicketViewHolder) holder).mType.setText("3+");
                ((TicketViewHolder) holder).mOdds.setText("2");
            } else {
                ((TicketViewHolder) holder).mBetType.setText("WINNER");
                if (genericList.get(position).GetNesto().get("result").equals("0"))
                    ((TicketViewHolder) holder).mType.setText("X");
                if (genericList.get(position).GetNesto().get("result").equals("1"))
                    ((TicketViewHolder) holder).mType.setText("1");
                if (genericList.get(position).GetNesto().get("result").equals("2"))
                    ((TicketViewHolder) holder).mType.setText("2");
                ((TicketViewHolder) holder).mOdds.setText(genericList.get(position).GetNesto().get("match_odds"));
            }
        }


    }

    @Override
    public int getItemCount() {
        return genericList.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_team1Ticket)
        TextView mTeam1Ticket;
        @BindView(R.id.tv_team2Ticket)
        TextView mTeam2Ticket;
        @BindView(R.id.tv_ticketBetType)
        TextView mBetType;
        @BindView(R.id.tv_ticketType)
        TextView mType;
        @BindView(R.id.tv_Odds)
        TextView mOdds;

        public TicketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void Clear(){
        genericList.clear();
        notifyDataSetChanged();
    }

    public class TicketHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_TicketId)
        TextView mTicketId;
        @BindView(R.id.tv_totalOdds)
        TextView mTotalOdds;
        @BindView(R.id.tv_success)
        TextView mSuccess;

        public TicketHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
