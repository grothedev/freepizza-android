package org.grothedev.freepizza;

import android.app.Activity;
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

    Activity callingActivity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        progress = (ProgressBar) objects[1];
        site = (Site) objects[0];
        callingActivity = (Activity) objects[2];

        APICaller.postSite(site);

        return null;
    }



    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progress.setVisibility(View.GONE);
        Log.d("progress bar", "gone");
        if (APICaller.success == true){
            Log.d("sucess", "added site");
            callingActivity.onBackPressed();
            AddSiteActivity.toast("Thank you for your contribution!", APICaller.mainActivity);
        } else {
            AddSiteActivity.toast("Food location was not able to be submitted. Try again.", APICaller.mainActivity);
            Log.d("failed", "did not add site");
        }
    }
}
