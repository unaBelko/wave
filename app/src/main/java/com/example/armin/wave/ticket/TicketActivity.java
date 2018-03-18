package com.example.armin.wave.ticket;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.armin.wave.R;
import com.example.armin.wave.adapter.TicketAdapter;
import com.example.armin.wave.ticket.model.Bet;
import com.example.armin.wave.ticket.model.ITicketGeneric;
import com.example.armin.wave.ticket.model.Item;
import com.example.armin.wave.ticket.model.TicketResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 29.10.2017..
 */

public class TicketActivity extends AppCompatActivity implements ITicketContract.View {
    @BindView(R.id.rv_ticektRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_Submit)
    Button mSubmit;
    @BindView(R.id.btn_Clear)
    Button mClear;
    @BindView(R.id.tv_total_odds)
    TextView mTotal;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    List<Item> generics;

    Context context;
    ITicketContract.Preseneter preseneter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);
        ButterKnife.bind(this);

        context = this;
        preseneter = new TicektPresenter(this, this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preseneter.Submit();
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preseneter.Clear();
            }
        });

        preseneter.getTickets("0");


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                preseneter.Remove(index,generics.get(index).getMatchID(),generics);

            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onTicketsFetched(TicketResponse response) {
        if (response.getBets().size() == 1) {

            generics = new ArrayList<>();

            for (Item item : response.getBets().get(0).getItems()) {
                generics.add(item);
            }
            mTotal.setText("Total:"+response.getBets().get(0).getTotalOdd());
            mAdapter = new TicketAdapter(this, generics);
            mRecyclerView.setAdapter(mAdapter);
        }


    }

    @Override
    public void onSubmit(boolean success, String message) {
        if (!success) {
            Toast.makeText(this, "Empty ticket!", Toast.LENGTH_SHORT).show();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onClear(boolean success, String message) {
        if (!success) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            if(generics != null) {
                generics.clear();
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRemove(List<Item> items) {
        generics = items;
        mAdapter.notifyDataSetChanged();
        preseneter.getTickets("0");
    }
}
