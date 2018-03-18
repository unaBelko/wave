package com.example.armin.wave.daily_results;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.armin.wave.R;
import com.example.armin.wave.adapter.ScheduleAdapter;
import com.example.armin.wave.utakmice.IUtakmiceContract;
import com.example.armin.wave.utakmice.model.GenericList;
import com.example.armin.wave.utakmice.model.Match;
import com.example.armin.wave.utakmice.model.Match_;
import com.example.armin.wave.utakmice.model.UtakmiceResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 28.10.2017..
 */

public class DailyResultsActivity extends AppCompatActivity implements IDailyResultsContract.View {
    @BindView(R.id.rv_schedule)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshMatches)
    SwipeRefreshLayout mRefresh;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    IDailyResultsContract.Presenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utakmice_activity);
        ButterKnife.bind(this);

        mPresenter = new DailyResultsPresenter(this,this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.LoadMatches();

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
                mPresenter.LoadMatches();
            }
        });
        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    public void OnMatchesLoad(String json) {
        if(mAdapter != null){
            ((ScheduleAdapter)mAdapter).Clear();
        }
        Gson gson = new Gson();
        UtakmiceResponse utakmiceResponse = gson.fromJson(json, UtakmiceResponse.class);
        List<GenericList> lista = new ArrayList<>();

        for (Match match: utakmiceResponse.getMatches()){
            lista.add(match);
            for (Match_ m : match.getMatches()){
                lista.add(m);
            }
        }
        Log.i("okokok","ok");
        mAdapter = new ScheduleAdapter(lista,this, null,false);
        mRecyclerView.setAdapter(mAdapter);
        mRefresh.setRefreshing(false);
    }
}
