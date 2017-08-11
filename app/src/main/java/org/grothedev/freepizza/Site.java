package org.grothedev.freepizza;

import android.os.Parcel;
import android.os.Parcelable;
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
    double probExists;

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
            this.probExists = jo.getDouble("prob_exists");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //when submitting new site
    public Site(String food, String info, String location, String day, String start, String end){
        this.info = info;
        this.day = day;
        this.start = start;
        this.end = end;
        this.food = food;
        this.location = location;
    }

    //empty site
    public Site(){

    }

    public String toString(){
        return location + " - " + food + "\n" + day + " - " + start + " to " + end;
    }

}
