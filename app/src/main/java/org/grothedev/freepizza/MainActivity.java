package org.grothedev.freepizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView siteListView;
    ArrayAdapter<Site> listAdapter;

    APICaller api;

    Site[] sites;
    String[] sitesStr; //currently testing the adapter with simple strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();

        api = new APICaller(this);
        new GetSitesTask().execute(api, siteListView);


        //wait for response, and update listview when data comes in

        //onclick on each site brings up new view showing more info
    }

    private void initUIElements(){
        sitesStr = new String[]{"test", "two"};

        siteListView = (ListView) findViewById(R.id.listViewSites);


    }
}
