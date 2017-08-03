package org.grothedev.freepizza;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by thomas on 02/08/17.
 */

public class AddSiteTask extends AsyncTask {

    Site site;

    @Override
    protected Object doInBackground(Object[] objects) {

        site = (Site) objects[0];
        Log.d("food", site.food);
        APICaller.postSite(site);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }
}
