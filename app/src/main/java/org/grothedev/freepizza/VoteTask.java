package org.grothedev.freepizza;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by thomas on 08/08/17.
 */

public class VoteTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        Vote v = (Vote) objects[0];

        APICaller.postVote(v);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }
}
