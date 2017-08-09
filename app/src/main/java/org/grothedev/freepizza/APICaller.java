package org.grothedev.freepizza;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * this class uses the json request methods in the http utils class to get/send data from/to the server.
 *
 *
 * Created by thomas on 29/07/17.
 */

public class APICaller {

    static RequestQueue requestQueue;


    static private final int SITES_INDEX = 0;
    static private final int SITES_SHOW = 1; //probably going to be unused
    static private final int SITES_LOCATION_SEARCH = 2;
    static private final int SITES_STORE = 4;
    static private final int VOTES_STORE = 5;
    static private final int VOTES_INDEX = 6; //probably going to be unused

    static Site[] sites; //should this be stored in this class?

    static boolean done = false; //used to signifity when the current operation is complete
    static boolean success = false; //signifies success of operation (ex: if a site has successfully been submitted to the server)

    static int timeout = 100;

    public static void getSites(){
        resetFlags();
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
                        handleResponse(new JSONArray(), SITES_INDEX); //empty json array to signify error
                        Log.d("error", "sites were not able to be downloaded");
                    }
                });

        requestQueue.add(request);

        waitForCompletion();
    }

    public static void postSite(Site site){
        String url = "http://gdev.ddns.net:8000/api/sites";

        final Site toAdd = site;

        Map<String, String> params = new HashMap<String, String>();
        params.put("food", toAdd.food);
        params.put("info", toAdd.info);
        params.put("day", toAdd.day);
        params.put("start", toAdd.start);
        params.put("end", toAdd.end);
        params.put("location", toAdd.location);
        params.put("votes_total", "0");
        params.put("votes_true", "0");

        makePostRequest(url, SITES_STORE, params);

        waitForCompletion();
    }

    public static void postVote(Vote v){
        String url = "http://gdev.ddns.net:8000/api/votes";
        final Map<String, String> params = new HashMap<>();
        params.put("site_id", Integer.toString(v.getSiteId()));
        params.put("true", Boolean.toString(v.getExists()));

        makePostRequest(url, VOTES_STORE, params); //TODO something is wrong with the form in which the data is being sent

        waitForCompletion();
    }

    private static void makePostRequest(String url, final int code, final Map<String, String> params){
        resetFlags();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        handleResponse(response, code);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("error", "error interacting with the server");

                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }

        };

        requestQueue.add(request);
    }

    private static void handleResponse(String response, int code){
        switch (code){
            case SITES_STORE:
                Log.d("add response", ""+response);

                if (response == null){
                    success = false;
                    done = true;
                    return;
                }
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if (jsonResponse.getInt("success") == 1){
                        success = true;
                    } else {
                        success = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                done = true;
                break;
            case VOTES_STORE:
                Log.d("vote response", ""+response);
                done = true;
                break;
        }
    }

    private static void handleResponse(JSONArray response, int code){
        switch (code){
            case SITES_INDEX:

                if (response.length() == 0) {
                    success = false;
                    sites = new Site[]{new Site("there was an error retreiving the locations from the server", "", "", "", "", "")};
                }
                else {
                    sites = new Site[response.length()];
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jo  = new JSONObject(response.get(i).toString());
                            sites[i] = new Site(jo);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    success = true;
                }

                done = true;
                break;
            case SITES_LOCATION_SEARCH:
                //TODO
                break;
            case VOTES_STORE:
                if (response.length() == 0){
                    success = false;
                    Log.d("response", "none");
                } else {
                    JSONObject jo = null;
                    Log.d("response", response.toString());
                    try {
                        jo = new JSONObject(response.get(0).toString());
                        if (jo.get("success") == "1"){
                            success = true;
                        } else {
                            success = false;
                        }
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

    private static void waitForCompletion(){
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

    private static void resetFlags(){
        done = false;
        success = false;
    }
}
