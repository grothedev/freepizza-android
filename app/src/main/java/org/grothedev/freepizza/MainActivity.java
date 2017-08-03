package org.grothedev.freepizza;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    ListView siteListView;
    ArrayAdapter<Site> listAdapter;
    Button addSiteButton;


    Site[] sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();
        initAPICaller();

        new GetSitesTask().execute(this, siteListView);


        //wait for response, and update listview when data comes in

        //onclick on each site brings up new view showing more info
    }

    private void initUIElements(){
        siteListView = (ListView) findViewById(R.id.listViewSites);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        addSiteButton = (Button) findViewById(R.id.buttonAddSite);
        addSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show fragment for entering data for new site
                /*AddSiteFragment addSiteFragment = new AddSiteFragment();
                fragmentTransaction.replace(R.id.container, addSiteFragment).addToBackStack("add_frag");

                fragmentTransaction.commit();
                */

                //trying activiity instead of fragment
                Intent addSiteIntent = new Intent(getApplicationContext(), AddSiteActivity.class);

                startActivity(addSiteIntent);
            }
        });

    }

    private void initAPICaller(){
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        APICaller.requestQueue = new RequestQueue(cache, network);
        APICaller.requestQueue.start();
    }

    /*public void onSubmitButtonPressed(Site site){
        APICaller.postSite(site);

    }*/
}
