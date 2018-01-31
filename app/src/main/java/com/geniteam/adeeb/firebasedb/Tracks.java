package com.geniteam.adeeb.firebasedb;

/**
 * Created by adeeb on 1/31/2018.
 */

public class Tracks {
    String trackId;
    String trackNmae;
    String rating;

    public Tracks() {

    }

    public Tracks(String trackId, String trackNmae, String rating) {
        this.trackId = trackId;
        this.trackNmae = trackNmae;
        this.rating = rating;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getTrackNmae() {
        return trackNmae;
    }

    public String getRating() {
        return rating;
    }
}
