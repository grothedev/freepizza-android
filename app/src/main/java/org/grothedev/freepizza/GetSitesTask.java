package org.grothedev.freepizza;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by thomas on 29/07/17.
 */

public class GetSitesTask extends AsyncTask {

    APICaller api;
    ListView sitesListView;

    @Override
    protected Object doInBackground(Object[] objects) {

        api = (APICaller) objects[0];
        api.getSites();

        //TODO i think i will also get the votes for each site here and put it into the sites array

        sitesListView = (ListView) objects[1];

        return null;
    }

    @Override
    protected void onPostExecute(Object result){

        ArrayAdapter<Site> listAdapter = new ArrayAdapter<Site>(api.context, R.layout.list_item, api.sites);
        sitesListView.setAdapter(listAdapter);
    }
}
