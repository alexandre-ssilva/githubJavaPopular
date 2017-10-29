package com.ssilvaalexandre.desafioandroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssilvaalexandre.desafioandroid.Model.RepositoryModel;
import com.ssilvaalexandre.desafioandroid.R;

import java.util.ArrayList;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class RepositoryAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    private static final int REPO_VIEW = 100, PROGRESS_VIEW = -1;

    private ArrayList<RepositoryModel> repos;
    private OnRecycleItemClickListener listener;

    public RepositoryAdapter (ArrayList<RepositoryModel> repos, OnRecycleItemClickListener listener) {
        this.repos = repos;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RepositoryViewHolder) {
            ((RepositoryViewHolder)holder).setValues(repos.get(position));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == REPO_VIEW) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_cell, parent, false);
            return new RepositoryViewHolder(v);
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_cell, parent, false);
        return new ProgressViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (repos.get(position) == null)
            return PROGRESS_VIEW;

        return REPO_VIEW;
    }

    protected class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private TextView repoName, repoDescription, repoForks, repoStars, ownerName, ownerType;
        private ImageView ownerAvatar;

        RepositoryViewHolder(View v) {
            super(v);

            repoName = v.findViewById(R.id.repository_name);
            repoDescription = v.findViewById(R.id.repository_description);
            repoForks = v.findViewById(R.id.repository_fork_number);
            repoStars = v.findViewById(R.id.repository_star_number);
            ownerName = v.findViewById(R.id.repository_user_name_textview);
            ownerType = v.findViewById(R.id.repository_full_name_textview);
        }

        void setValues(RepositoryModel repos) {
            repoName.setText(repos.getName());
            repoDescription.setText(repos.getDescription());
            repoForks.setText("" + repos.getForksNumber());
            repoStars.setText("" + repos.getStarsNumber());
            ownerName.setText(repos.getOwner().getLoginName());
            ownerType.setText(repos.getOwner().getType());
        }
    }

    protected class ProgressViewHolder extends RecyclerView.ViewHolder {

        ProgressViewHolder (View v) {
            super(v);
        }
    }
}
