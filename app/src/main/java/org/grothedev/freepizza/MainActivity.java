package org.grothedev.freepizza;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.gc.materialdesign.views.ButtonFloat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    ListView siteListView;
    ButtonFloat addSiteButton;
    SwipeRefreshLayout refreshLayout;



    //set up a listview of all the "sites". a site is just the term i use to describe a location with its food and other info.
    //set up the apicaller which uses a RequestQueue from the volley library to make requests to the server
    //there is a button to allow the user to add new sites
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();
        initAPICaller();

        getSites();
    }

    private void initUIElements(){
        siteListView = (ListView) findViewById(R.id.listViewSites);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



        addSiteButton = (ButtonFloat) findViewById(R.id.buttonAddSite);
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

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayoutList);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSites();
            }
        });

    }

    private void getSites(){
        new GetSitesTask().execute(this, siteListView);
    }

    private void initAPICaller(){
        //setting up the request queue which will allow easily making post and get requests
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        APICaller.requestQueue = new RequestQueue(cache, network);
        APICaller.requestQueue.start();

        //giving main activity so can toast on result of requests
        APICaller.mainActivity = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_about:
                DialogFragment aboutFrag = new AboutFragment();
                aboutFrag.show(getFragmentManager(), "About");
                return true;
            case R.id.menu_refresh:
                refreshLayout.setRefreshing(true);
                getSites();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume(){
        super.onResume();
        getSites();
    }
}
