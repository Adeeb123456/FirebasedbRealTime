package com.geniteam.adeeb.firebasedb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by adeeb on 1/31/2018.
 */

public class Artist {
    String artistId;
    String artiistName;
    String artistgeneric;

    public Artist(String artistId, String artiistName, String artistgeneric) {
        this.artistId = artistId;
        this.artiistName = artiistName;
        this.artistgeneric = artistgeneric;
    }

    public Artist() {


    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtiistName() {
        return artiistName;
    }

    public String getArtistgeneric() {
        return artistgeneric;
    }
}
