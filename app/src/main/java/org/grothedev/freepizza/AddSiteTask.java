package org.grothedev.freepizza;

import android.os.AsyncTask;

/**
 * Created by thomas on 02/08/17.
 */

public class AddSiteTask extends AsyncTask {

    Site site;

    @Override
    protected Object doInBackground(Object[] objects) {
        site = (Site) objects[0];
        return null;
    }
}
