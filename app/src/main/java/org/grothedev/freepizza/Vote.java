package org.grothedev.freepizza;

/**
 * Created by thomas on 06/08/17.
 */

public class Vote {
    private int siteId;
    private boolean exists;

    public Vote(int siteId, boolean exists){
        this.siteId = siteId;
        this.exists = exists;
    }

    boolean getExists(){
        return exists;
    }

    int getSiteId(){
        return siteId;
    }
}
