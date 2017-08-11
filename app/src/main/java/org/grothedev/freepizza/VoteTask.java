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
        if (APICaller.success){
            AddSiteActivity.toast("Successfully voted", APICaller.mainActivity);
        } else {
            if (APICaller.msg.equals("dupe")){
                AddSiteActivity.toast("You have already voted on the existence of food at this location", APICaller.mainActivity);
            } else {
                AddSiteActivity.toast("There was an unknown error. Please contact the developer upon persistence of the error.", APICaller.mainActivity);
            }
        }
    }
}
