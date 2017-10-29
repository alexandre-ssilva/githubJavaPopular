package com.ssilvaalexandre.desafioandroid.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.ssilvaalexandre.desafioandroid.BuildConfig;
import com.ssilvaalexandre.desafioandroid.Model.RepositoriesController;
import com.ssilvaalexandre.desafioandroid.ObjectManager.RepositoryManager;

/**
 * Created by alexandre on 28/10/17.
 * Nexaaas All rights reserved
 */

public class RepositoryAsyncTasks {

    public static class GetGithubJavaRepoAsyncTask extends AsyncTask<Integer, Void, RepositoriesController> {

        private String msg;
        private OnGetRepoAsyncTaskListener listener;

        public GetGithubJavaRepoAsyncTask (OnGetRepoAsyncTaskListener listener) {
            this.listener = listener;
        }

        @Override
        protected RepositoriesController doInBackground(Integer... page) {
            try {
                return RepositoryManager.getRepositories(page[0]);
            } catch (Exception e) {
                if (BuildConfig.DEBUG)
                    Log.e("GITHUB-JAVA-LANG",Thread.currentThread().getStackTrace()[2].getClassName() +
                    "#" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " error line #" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" +
                    e.getLocalizedMessage());

                msg = e.getLocalizedMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(RepositoriesController repositoriesController) {
            if (listener != null) {
                if (repositoriesController != null)
                    listener.onGetRepositories(repositoriesController);
                else
                    listener.onGetRepositoriesFailed(msg);
            }
        }
    }

    public interface OnGetRepoAsyncTaskListener {
        void onGetRepositories (RepositoriesController controller);
        void onGetRepositoriesFailed (String msg);
    }
}
