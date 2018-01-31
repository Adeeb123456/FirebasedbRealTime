package com.geniteam.adeeb.firebasedb;

import android.databinding.DataBindingUtil;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.geniteam.adeeb.firebasedb.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ActivityMainBinding mainBinding;
    FirebaseDatabase database ;
    DatabaseReference artistDbref;
    DatabaseReference trackDbref;
    List<Artist> artistList=new ArrayList<>();
    List<Tracks> tracksList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mainBinding=  DataBindingUtil.setContentView(this,R.layout.activity_main);

artistDbref=FirebaseDatabase.getInstance().getReference("artist");

        mainBinding.button2.setOnClickListener(this);
        mainBinding.addtrack.setOnClickListener(this);
        mainBinding.viewtrack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==mainBinding.button2){
addArtist();
        }else if(view==mainBinding.addtrack)
        {
         trackDbref=FirebaseDatabase.getInstance().getReference().child(artistId);
         String trackID=trackDbref.push().getKey();
         Tracks tracks=new Tracks(trackID,"trackname","rating3");
         trackDbref.child(trackID).setValue(tracks);
        }else if(view==mainBinding.viewtrack)
        {
            trackDbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tracksList.clear();
                    for(DataSnapshot snapshotTrack:dataSnapshot.getChildren()){
                       Tracks tracks;
                       tracks=snapshotTrack.getValue(Tracks.class);
                       tracksList.add(tracks);
                    }
                Toast.makeText(getApplicationContext(),"track size "+tracksList.size()
                +" name "+tracksList.get(tracksList.size()-1).getTrackNmae(),Toast.LENGTH_LONG).show();
                }



                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }

    public void addArtist(){
        String name=mainBinding.editText.getText().toString().trim();
        String genric=mainBinding.spinner.getSelectedItem().toString();
String id=artistDbref.push().getKey();

Artist artist=new Artist(id,name,genric);
artistDbref.child(id).setValue(artist);


        if(!TextUtils.isEmpty(name)){

        }
    }
String artistId;
    StringBuilder stringBuilder=new StringBuilder();
    @Override
    protected void onStart() {
        super.onStart();
        artistDbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot snapshotArtist:dataSnapshot.getChildren()){
                    Artist artist;
                    artist=snapshotArtist.getValue(Artist.class);

 artistList.add(artist);
                }
artistId=artistList.get(artistList.size()-1).getArtistId();
                Toast.makeText(getApplicationContext(),"size of db "+artistList.size()
                +" name "+artistList.get(artistList.size()-1).artiistName,Toast.LENGTH_LONG).show();
                //add list to listView Adapter
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
