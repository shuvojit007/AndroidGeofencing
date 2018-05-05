package com.example.shuvojit.shuvojit_geofences.Implementaion;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;

import java.util.UUID;

public class GeofencingService {

    Context cn;

    public GeofencingService(Context cn) {
        this.cn =cn;
    }

    public static void CreateGeonfencing(Context cn, RealmService realmService, String rad, Double llt, Double llng, String td, String plc){
        String id = UUID.randomUUID().toString();
       realmService.save(id,rad,llt,llng,td,plc);
    }

    public  GeofencingClient  init(GeofencingClient gc){
        return gc= LocationServices.getGeofencingClient(cn);
    }
}
