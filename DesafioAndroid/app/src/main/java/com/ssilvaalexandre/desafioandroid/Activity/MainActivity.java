package com.ssilvaalexandre.desafioandroid.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ssilvaalexandre.desafioandroid.Adapter.OnRecycleItemClickListener;
import com.ssilvaalexandre.desafioandroid.Adapter.RepositoryAdapter;
import com.ssilvaalexandre.desafioandroid.AsyncTasks.AsyncTasks;
import com.ssilvaalexandre.desafioandroid.Model.RepositoriesController;
import com.ssilvaalexandre.desafioandroid.R;
import com.ssilvaalexandre.desafioandroid.Util.DividerItemDecoration;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        AsyncTasks.OnGetRepoAsyncTaskListener, OnRecycleItemClickListener, RepositoryAdapter.OnLoadingListener {

    private SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;
    private int page = 0;
    private RepositoriesController repositoriesController;
    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        onActivityCreated();
    }

    private void setupToolbar () {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.github_java_pop);
    }

    private void onActivityCreated () {
        swipe = findViewById(R.id.main_swipe);
        recyclerView = findViewById(R.id.main_recycler);

        swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorAccent));
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
            }
        });

        getRepositories();
    }

    private void getRepositories () {
        new AsyncTasks.GetGithubJavaRepoAsyncTask(this).execute(++page);
    }

    @Override
    public void onRefresh() {
        page = 0;

        if (repositoriesController != null)
            repositoriesController.clearRepositories();

        new AsyncTasks.GetGithubJavaRepoAsyncTask(this).execute(++page);
    }

    @Override
    public void onGetRepositories(RepositoriesController controller) {

        if (swipe.isRefreshing())
            swipe.setRefreshing(false);

        if (repositoriesController == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            repositoriesController = controller;
            adapter = new RepositoryAdapter(repositoriesController.getRepos(), this, recyclerView);
            adapter.setLoadingListener(this);
            recyclerView.setAdapter(adapter);

            DividerItemDecoration decor = new DividerItemDecoration(getResources().getDrawable(R.drawable.divider_item), true, true);
            decor.setSize(getResources().getDimensionPixelSize(R.dimen.item_separator));

            recyclerView.addItemDecoration(decor);
        } else {
            adapter.setLoading(false);

            repositoriesController.addRepositories(controller.getRepos());

            adapter.notifyItemRangeChanged(repositoriesController.getRepos().size()
                    - controller.getRepos().size(), controller.getRepos().size());
        }
    }

    @Override
    public void onGetRepositoriesFailed(String msg) {
        if (adapter != null)
            adapter.setLoading(false);
        if (swipe.isRefreshing())
            swipe.setRefreshing(false);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecycleItemClick(int position) {
        Toast.makeText(this, repositoriesController.getRepositoryAtIndex(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void performLoad() {
        adapter.setLoading(true);
        getRepositories();
    }
}
