package org.grothedev.freepizza;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by thomas on 29/07/17.
 */

public class GetSitesTask extends AsyncTask {



    @Override
    protected Object doInBackground(Object[] objects) {

        APICaller api = (APICaller) objects[0];
        api.getSites();
        return null;
    }

    protected void onPostExecute(Long result){

    }
}
