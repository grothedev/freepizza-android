package org.grothedev.freepizza;

import android.content.Context;

/**
 * this class uses the json request methods in the http utils class to get/send data from/to the server, and then
 *
 * Created by thomas on 29/07/17.
 */

public class APICaller {

    HttpUtils h;

    public APICaller(Context context){
        h = new HttpUtils(context);
    }

    public void getSites(){

    }
}
