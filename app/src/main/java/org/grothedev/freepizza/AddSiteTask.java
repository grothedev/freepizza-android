package org.grothedev.freepizza;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.gc.materialdesign.widgets.ProgressDialog;

/**
 * Created by thomas on 02/08/17.
 */

public class AddSiteTask extends AsyncTask {

    Site site;

    ProgressBar progress;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        progress = (ProgressBar) objects[1];
        site = (Site) objects[0];
        APICaller.postSite(site);

        return null;
    }



    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progress.setVisibility(View.GONE);
    }
}
