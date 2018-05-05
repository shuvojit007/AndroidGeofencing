package com.example.shuvojit.shuvojit_geofences;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("todo01")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static App getInstance() {
        return instance;
    }
}
