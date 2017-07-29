package org.grothedev.freepizza;

/**
 * Created by thomas on 29/07/17.
 *
 * a site object, which represents a site at which there is food
 */

public class Site {
    int id;
    String description;
    String date, start, end;
    String food;
    String location;
    int votesTotal, votesTrue;

    public Site(){
        //TODO i think this will take the json response object
    }
}
