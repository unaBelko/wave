package com.example.armin.wave.TicketHistory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.armin.wave.R;
import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.StringsPositions;
import com.example.armin.wave.adapter.HistoryAdapter;
import com.example.armin.wave.network.UtakmiceService;
import com.example.armin.wave.ticket.model.Bet;
import com.example.armin.wave.ticket.model.ITicketGeneric;
import com.example.armin.wave.ticket.model.Item;
import com.example.armin.wave.ticket.model.TicketResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 29.10.2017..
 */

public class TicketHistory extends AppCompatActivity {
    @BindView(R.id.rv_history)
    RecyclerView rvR;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mRefresh;
    List<ITicketGeneric> genericList;
    RecyclerView.Adapter mAdapter = null;
    RecyclerView.LayoutManager mLayoutManager;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_history);
        ButterKnife.bind(this);
        Log.i("position", StringsPositions.getPosition(1));

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });
        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        rvR.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rvR.setLayoutManager(mLayoutManager);
        fetchData();



    }
    void fetchData(){
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(this, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");
        UtakmiceService service = new UtakmiceService();
        service.GetUtakmiceApi().ticketGet("PHPSESSID=" + sessionID,"1").enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                FillIt(response.body());

            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
            }
        });


    }
    void FillIt(TicketResponse response){
        if (mAdapter != null){
            ((HistoryAdapter)mAdapter).Clear();
        }
        genericList = new ArrayList<>();
        for (Bet bet : response.getBets()){
            genericList.add(bet);
            for (Item item : bet.getItems()){
                genericList.add(item);
            }
        }
        Log.i("kolicina", String.valueOf(genericList.size()));
        mAdapter = new HistoryAdapter(this,genericList);
        rvR.setAdapter(mAdapter);
        mRefresh.setRefreshing(false);
    }
}
