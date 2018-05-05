package com.example.shuvojit.shuvojit_geofences.Implementaion;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel;
import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel02;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmService {

    private static Realm realm;
    private List<MapModel02> md ;

    public RealmService(Realm realm) {
        this.realm = realm;
    }

    public void Close() {
        realm.close();
    }

    public void save(String id, final String radius, Double llt, final Double llng, final String todoed, final String placename) {
        realm.executeTransaction(realm -> {
                    MapModel model = realm.createObject(MapModel.class);
                    model.setLat(llt);
                    model.setLng(llng);
                    model.setPlaceName(placename);
                    model.setRadius(Integer.parseInt(radius));
                    model.setTodo(todoed);
                    model.setKey(id);

                }

        );
        UpdateModel();
    }


    public void  UpdateModel(){
        md = new ArrayList<>();
        RealmResults<MapModel> todoItems = realm.where(MapModel.class).findAll();
        for (MapModel mp : todoItems) {
            md.add(new MapModel02(mp.getLat(),mp.getLng(),mp.getPlaceName(),mp.getRadius(),mp.getTodo(),mp.getKey()));
        }


    }

    public  List<MapModel02> GetLocationMoel(Context cn){
        if(md==null){
            UpdateModel();
            Toast.makeText(cn, String.valueOf(md.size()), Toast.LENGTH_SHORT).show();
            return md;
        }
        Log.d("Test",String.valueOf(md.size()));
        Toast.makeText(cn, String.valueOf(md.size()), Toast.LENGTH_SHORT).show();
        return md;
    }

}
