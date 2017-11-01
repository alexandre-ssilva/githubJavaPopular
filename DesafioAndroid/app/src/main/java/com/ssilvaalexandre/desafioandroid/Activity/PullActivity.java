package com.ssilvaalexandre.desafioandroid.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ssilvaalexandre.desafioandroid.Adapter.OnRecycleItemClickListener;
import com.ssilvaalexandre.desafioandroid.Adapter.PullAdapter;
import com.ssilvaalexandre.desafioandroid.Model.PullRequestModel;
import com.ssilvaalexandre.desafioandroid.R;
import com.ssilvaalexandre.desafioandroid.Util.DividerItemDecoration;

import java.util.ArrayList;

public class PullActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnRecycleItemClickListener {

    private SwipeRefreshLayout swipe;
    private PullAdapter adapter;
    private ArrayList<PullRequestModel>pulls;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_recycler_list);

        setupToolbar();
        onActivityCreated();
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void onActivityCreated () {
        swipe = findViewById(R.id.main_swipe);
        recyclerView = findViewById(R.id.main_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decor = new DividerItemDecoration(getResources().getDrawable(R.drawable.divider_item), true, true);
        decor.setSize(getResources().getDimensionPixelSize(R.dimen.item_separator));

        recyclerView.addItemDecoration(decor);
    }

    private void getPulls () {
        
    }

    @Override
    public void onRefresh() {
        if (pulls != null) {
            pulls.clear();

            if (adapter != null)
                adapter.notifyDataSetChanged();
        }

        getPulls ();
    }

    @Override
    public void onRecycleItemClick(int position) {

    }
}
