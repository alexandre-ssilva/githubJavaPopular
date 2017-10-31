package com.ssilvaalexandre.desafioandroid.Adapter;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssilvaalexandre.desafioandroid.BuildConfig;
import com.ssilvaalexandre.desafioandroid.Model.RepositoryModel;
import com.ssilvaalexandre.desafioandroid.ObjectManager.UserManager;
import com.ssilvaalexandre.desafioandroid.R;

import java.util.ArrayList;
import java.util.Locale;

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
        private View clickableCell;

        RepositoryViewHolder(View v) {
            super(v);

            repoName = v.findViewById(R.id.repository_name);
            repoDescription = v.findViewById(R.id.repository_description);
            repoForks = v.findViewById(R.id.repository_fork_number);
            repoStars = v.findViewById(R.id.repository_star_number);
            ownerName = v.findViewById(R.id.repository_user_name_textview);
            ownerType = v.findViewById(R.id.repository_full_name_textview);
            ownerAvatar = v.findViewById(R.id.repository_owner_avatar);
            clickableCell = v.findViewById(R.id.repository_clickable_cell);
        }

        void setValues(RepositoryModel repos) {
            repoName.setText(repos.getName());
            repoDescription.setText(repos.getDescription());
            repoForks.setText(String.format(Locale.getDefault(), "%d",  repos.getForksNumber()));
            repoStars.setText(String.format(Locale.getDefault(), "%d", repos.getStarsNumber()));
            ownerName.setText(repos.getOwner().getLoginName());
            ownerType.setText(repos.getOwner().getType());

            new GetUserAvatarAsyncTask(this).execute(repos.getOwner().getAvatarUrl());
            clickableCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onRecycleItemClick(getAdapterPosition());
                }
            });
        }
    }

    protected class ProgressViewHolder extends RecyclerView.ViewHolder {

        ProgressViewHolder (View v) {
            super(v);
        }
    }

    protected class GetUserAvatarAsyncTask extends AsyncTask<String, Void, Drawable> {

        private RepositoryViewHolder holder;

        GetUserAvatarAsyncTask (RepositoryViewHolder holder) {
            this.holder = holder;
        }

        @Override
        protected Drawable doInBackground(String... strings) {
            try {
                return UserManager.getUserAvatar(strings[0]);
            } catch (Exception e) {
                if (BuildConfig.DEBUG)
                    Log.e("GITHUB_JAVA_REPO", Thread.currentThread().getStackTrace()[2].getClassName() +
                    "#" + Thread.currentThread().getStackTrace()[2].getMethodName() + " error line #" +
                    Thread.currentThread().getStackTrace()[2].getLineNumber() + ": " +
                    e.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (drawable != null)
                holder.ownerAvatar.setImageDrawable(drawable);
        }
    }
}
