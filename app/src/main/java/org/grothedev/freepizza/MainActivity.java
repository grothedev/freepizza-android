package org.grothedev.freepizza;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AddSiteFragment.OnSubmitListener{

    ListView siteListView;
    ArrayAdapter<Site> listAdapter;
    Button addSiteButton;

    APICaller api;

    Site[] sites;

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
        siteListView = (ListView) findViewById(R.id.listViewSites);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        addSiteButton = (Button) findViewById(R.id.buttonAddSite);
        addSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show fragment for entering data for new site
                AddSiteFragment addSiteFragment = new AddSiteFragment();
                fragmentTransaction.add(android.R.id.content, addSiteFragment);

                fragmentTransaction.commit();
            }
        });

    }

    public void onSubmitButtonPressed(Site site){
        if (api.postSite(site)){
            //add to sites
        } else {
            //report failure
        }

    }
}
