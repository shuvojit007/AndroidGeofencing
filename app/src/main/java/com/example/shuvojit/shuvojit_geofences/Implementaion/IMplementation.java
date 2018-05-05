package com.example.shuvojit.shuvojit_geofences.Implementaion;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel;
import com.example.shuvojit.shuvojit_geofences.Implementaion.Model.MapModel02;
import com.example.shuvojit.shuvojit_geofences.R;
import com.example.shuvojit.shuvojit_geofences.example02.MainActivity02;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class IMplementation extends AppCompatActivity {
    Context cn;
    private RecyclerView mrec;
    private RealmService realmService;
    GeofencingService geofencingService;

    public List<MapModel02> mapmodel;
    public LocationAdapter adapter;


    Double llt = 0.0;
    Double llng = 0.0;
    private FloatingActionButton fab;
    Realm realm;
    private TextView placename;
    private static final String TAG = IMplementation.class.getSimpleName();

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implementation);
        cn = this;
        geofencingService = new GeofencingService(cn);
        init();


    }


    private void init() {
        realm = Realm.getDefaultInstance();
        realmService = new RealmService(realm);
       // realm.addChangeListener(realm -> adapter.notifyDataSetChanged());
        mapmodel=realmService.GetLocationMoel(cn);

        mrec = findViewById(R.id.mrec);
        mrec.setLayoutManager(new LinearLayoutManager(cn));
        mrec.setItemAnimator(new DefaultItemAnimator());
//        adapter =new LocationAdapter(realm.where(MapModel.class).findAll(),cn);
        adapter =new LocationAdapter(mapmodel,cn);
        mrec.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> OpenDialog());
    }



    private void OpenDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.create_reminder, null);
        final EditText todoed = view.findViewById(R.id.picklocation);
        placename = view.findViewById(R.id.pickpkace);
        placename.setOnClickListener(v -> {
            if (isServiceOk()) getLocation();
        });
        final EditText radius = view.findViewById(R.id.pickradius);
        Button done = view.findViewById(R.id.save);
        Dialog dialog = new Dialog(cn);
        dialog.setContentView(view);
        done.setOnClickListener(v -> {
            String rad = radius.getText().toString();
            String todo = todoed.getText().toString();
            String place = placename.getText().toString();
            if (!rad.equals("") || !todo.equals("") || !place.equals("") || llng != 0.0 || llt != 0) {
                GeofencingService.CreateGeonfencing(cn, realmService, rad, llt, llng, todo, place);

                mapmodel=realmService.GetLocationMoel(cn);
                adapter.notifyDataSetChanged();
                dialog.dismiss();


            } else {
                Toast.makeText(cn, "Null", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }


    //todo--------------- Place Picker scope---------------------
    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOk: ");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(cn);
        if (available == ConnectionResult.SUCCESS) {
            // everything is fine and the user can make map requests
            Log.d(TAG, "isServiceOk: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // an error occured but we can resolve it
            Log.d(TAG, "isServiceOk: an error occared but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(IMplementation.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void getLocation() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(IMplementation.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            Log.e(TAG, "onClick: GooglePlayServicesRepairableException: " + e.getMessage());
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e(TAG, "onClick: GooglePlayServicesNotAvailableException: " + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                if (place == null) {
                    Log.i(TAG, "No place selected");
                    return;
                }
                String locationName = place.getName() + ", " + place.getAddress();
                LatLng location_coordinate = place.getLatLng();
                llt = place.getLatLng().latitude;
                llng = place.getLatLng().longitude;
                placename.setText(locationName);
                Toast.makeText(cn, "Name" + locationName, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Can not get tvLocation. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }//todo called markerforgeofences
    //todo--------------- Place Picker scope---------------------
}
