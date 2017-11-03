package com.ssilvaalexandre.desafioandroid.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ssilvaalexandre.desafioandroid.Adapter.OnRecycleItemClickListener;
import com.ssilvaalexandre.desafioandroid.Adapter.PullAdapter;
import com.ssilvaalexandre.desafioandroid.AsyncTasks.AsyncTasks;
import com.ssilvaalexandre.desafioandroid.Model.PullRequestModel;
import com.ssilvaalexandre.desafioandroid.R;
import com.ssilvaalexandre.desafioandroid.Util.DividerItemDecoration;

import java.util.ArrayList;

public class PullActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, OnRecycleItemClickListener,
        AsyncTasks.OnGetPullRequestsListener {

    private SwipeRefreshLayout swipe;
    private PullAdapter adapter;
    private ArrayList<PullRequestModel>pulls;
    private RecyclerView recyclerView;
    private String repository, pullUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_recycler_list);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            repository = extras.getString("repository");
            pullUrl = extras.getString("pulls");
        }

        setupToolbar();
        onActivityCreated();

    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(repository);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    private void onActivityCreated () {
        swipe = findViewById(R.id.main_swipe);
        recyclerView = findViewById(R.id.main_recycler);

        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorAccent));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decor =
                new DividerItemDecoration(getResources().
                        getDrawable(R.drawable.divider_item), true, true);
        decor.setSize(getResources().getDimensionPixelSize(R.dimen.item_separator));

        recyclerView.addItemDecoration(decor);

        if (pullUrl != null)
            getPulls();
    }

    private void getPulls () {
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
            }
        });

        new AsyncTasks.GetPullRequestsAsyncTask(this).execute(pullUrl);
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
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pulls.get(position).getSelfUrl()));
        startActivity(intent);
    }

    @Override
    public void onGetPullRequests(ArrayList<PullRequestModel> pulls) {
        if (swipe.isRefreshing())
            swipe.setRefreshing(false);

        if (adapter == null) {
            this.pulls = pulls;
            adapter = new PullAdapter(this.pulls);
            recyclerView.setAdapter(adapter);
            adapter.setOnClickListener(this);
        } else {
            this.pulls.addAll(pulls);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onGetPullRequestsFailed(String msg) {

        if (swipe.isRefreshing())
            swipe.setRefreshing(false);

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }
}
