package org.grothedev.freepizza;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thomas on 29/07/17.
 *
 * a site object, which represents a site at which there is food
 */

public class Site {
    int id;
    String info;
    String day, start, end;
    String food;
    String location;
    int votesTotal, votesTrue;

    //receives a json object from json array representing a site
    public Site(JSONObject jo){
        try {
            this.id = jo.getInt("id");
            this.info = jo.getString("info");
            this.day = jo.getString("day");
            this.start = jo.getString("start");
            this.end = jo.getString("end");
            this.food = jo.getString("food");
            this.location = jo.getString("location");
            this.votesTotal = jo.getInt("votes_total");
            this.votesTrue = jo.getInt("votes_true");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
