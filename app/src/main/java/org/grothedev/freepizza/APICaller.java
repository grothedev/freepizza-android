package org.grothedev.freepizza;

import android.content.Context;
import android.util.Log;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * this class uses the json request methods in the http utils class to get/send data from/to the server, and then
 *
 * Created by thomas on 29/07/17.
 */

public class APICaller {

    RequestQueue requestQueue;
    Context context;

    private final int SITES_INDEX = 0;
    private final int SITES_SHOW = 1;
    private final int SITES_LOCATION_SEARCH = 2;

    Site[] sites; //should this be stored in this class?

    boolean done = false;
    int timeout = 100;

    public APICaller(Context context){
        this.context = context;
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void getSites(){
        String url = "http://gdev.ddns.net:8000/api/sites";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        handleResponse(response, SITES_INDEX);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("error", error.getMessage());
                    }
                });

        requestQueue.add(request);

        int c = 0;
        while (!done && c < timeout){ //wait for sites to be downlaoded
            c++;
            try {
                new Thread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }



    private void handleResponse(JSONArray response, int code){
        switch (code){
            case SITES_INDEX:

                sites = new Site[response.length()];
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jo  = new JSONObject(response.get(i).toString());
                        sites[i] = new Site(jo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                done = true;
                break;
            
            default:
                break;

        }
    }
}
