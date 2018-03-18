package com.example.armin.wave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armin.wave.R;
import com.example.armin.wave.StringsPositions;
import com.example.armin.wave.ticket.model.Bet;
import com.example.armin.wave.ticket.model.ITicketGeneric;
import com.example.armin.wave.ticket.model.Item;
import com.example.armin.wave.ticket.model.TicketResponse;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 29.10.2017..
 */

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Item> generics;

    public TicketAdapter(Context context, List<Item> generics) {
        this.context = context;
        this.generics = generics;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ticket_row_sample, parent, false);
        return new TicketViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String homeTeam = generics.get(position).getHomeName();
        String awayTeam = generics.get(position).getAwayName();

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
        String type = generics.get(position).getResult();
        if (type == null) {
            ((TicketViewHolder) holder).mBetType.setText("GOALS");
            if (generics.get(position).getMorethan2().equals("0"))
                ((TicketViewHolder) holder).mType.setText("-3");
            if (generics.get(position).getMorethan2().equals("1"))
                ((TicketViewHolder) holder).mType.setText("3+");
            ((TicketViewHolder) holder).mOdds.setText("2");
        } else {
            ((TicketViewHolder) holder).mBetType.setText("WINNER");
            if (generics.get(position).getResult().equals("0"))
                ((TicketViewHolder) holder).mType.setText("X");
            if (generics.get(position).getResult().equals("1"))
                ((TicketViewHolder) holder).mType.setText("1");
            if (generics.get(position).getResult().equals("2"))
                ((TicketViewHolder) holder).mType.setText("2");
            ((TicketViewHolder) holder).mOdds.setText(String.valueOf( generics.get(position).getMatchOdds()));

        }

    }

    @Override
    public int getItemCount() {
        return generics.size();
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

}
