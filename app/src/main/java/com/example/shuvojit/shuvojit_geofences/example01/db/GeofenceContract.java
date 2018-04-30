package com.example.shuvojit.shuvojit_geofences.example01.db;

import android.provider.BaseColumns;

/*we ha implemented an Interface called BaseColumns
(which adds common attributes like id and count)
and we had declared few things that can be easily understood.*/

public class GeofenceContract {
    public GeofenceContract() { }

    public static class GeofenceEntry implements BaseColumns {
        public static final String TABLE_NAME = "geofences";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_LAT = "lat";
        public static final String COLUMN_NAME_LNG = "lng";
        public static final String COLUMN_NAME_EXPIRES = "expires";
    }
}
