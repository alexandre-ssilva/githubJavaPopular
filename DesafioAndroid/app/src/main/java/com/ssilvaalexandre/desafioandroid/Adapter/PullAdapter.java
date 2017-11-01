package com.ssilvaalexandre.desafioandroid.Adapter;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ssilvaalexandre.desafioandroid.BuildConfig;
import com.ssilvaalexandre.desafioandroid.Model.PullRequestModel;
import com.ssilvaalexandre.desafioandroid.ObjectManager.UserManager;
import com.ssilvaalexandre.desafioandroid.R;

import java.util.ArrayList;

/**
 * Created by alexandre on 01/11/17.
 * Nexaaas All rights reserved
 */

public class PullAdapter extends RecyclerView.Adapter <PullAdapter.PullViewHolder> {

    private ArrayList<PullRequestModel> pulls;
    private OnRecycleItemClickListener listener;

    public PullAdapter (ArrayList<PullRequestModel> pulls) {
        this.pulls = pulls;
    }

    public void setOnClickListener(OnRecycleItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return pulls.size();
    }

    @Override
    public long getItemId(int position) {
        return pulls.get(position).getId();
    }

    @Override
    public void onBindViewHolder(PullViewHolder holder, int position) {
        holder.setValues(pulls.get(position));
    }

    @Override
    public PullViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_cell, parent, false);

        return new PullViewHolder(v);
    }

    protected class PullViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView, descriptionTextView, ownerNameTextView, ownerTypeTextView;
        private CircularImageView ownerAvatar;

        PullViewHolder (View view) {
            super(view);

            titleTextView = view.findViewById(R.id.pull_title);
            descriptionTextView = view.findViewById(R.id.pull_description);
            ownerNameTextView = view.findViewById(R.id.pull_user_name);
            ownerTypeTextView = view.findViewById(R.id.pull_user_type);
            ownerAvatar = view.findViewById(R.id.pull_user_avatar);

            (view.findViewById(R.id.pull_cell_clickable)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onRecycleItemClick(getAdapterPosition());
                }
            });
        }

        void setValues (PullRequestModel pull) {
            titleTextView.setText(pull.getPullName());
            descriptionTextView.setText(pull.getPullBody());
            ownerNameTextView.setText(pull.getOwner().getLoginName());
            ownerTypeTextView.setText(pull.getOwner().getType());


        }
    }

    protected class GetAvatarAsynctask extends AsyncTask<String, Void, Drawable> {

        private PullViewHolder holder;

        GetAvatarAsynctask (PullViewHolder holder) {
            this.holder = holder;
        }

        @Override
        protected Drawable doInBackground(String... strings) {

            try {
                return UserManager.getUserAvatar(strings[0]);
            } catch (Exception e) {
                if (BuildConfig.DEBUG)
                    Log.e ("GITHUB-JAVA_POP", Thread.currentThread().getStackTrace()[2].getClassName() +
                    "#" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " error line #" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    ": " + e.getLocalizedMessage());
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
