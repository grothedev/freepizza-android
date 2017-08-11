package org.grothedev.freepizza;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by thomas on 29/07/17.
 */

public class GetSitesTask extends AsyncTask {

    ListView sitesListView;
    Activity context;

    @Override
    protected Object doInBackground(Object[] objects) {

        this.context = (Activity) objects[0];

        APICaller.getSites();

        sitesListView = (ListView) objects[1];

        return null;
    }

    @Override
    protected void onPostExecute(Object result){

        ((SwipeRefreshLayout)context.findViewById(R.id.refreshLayoutList)).setRefreshing(false);

        if (APICaller.success == false){
            AddSiteActivity.toast("There was an error retrieving the locations from the server", context);
            return;
        }

        ArrayAdapter<Site> listAdapter = new ArrayAdapter<Site>(context, R.layout.list_item, APICaller.sites);
        sitesListView.setAdapter(listAdapter);
        sitesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make an alert dialog with yes or no does the food location exists (pass the site id to the fragment)


                int siteId = ((Site)sitesListView.getItemAtPosition(position)).id;
                ExistsDialogFragment existsDialog = ExistsDialogFragment.newInstance(siteId);

                existsDialog.show(context.getFragmentManager(), "voteExists");
            }
        });
    }
}
