package com.example.shuvojit.shuvojit_geofences.Implementaion.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MapModel extends RealmObject implements Parcelable {
    private double lat;
    private double lng;
    private String PlaceName;
    private int radius;

    private String todo;
    private String key;

    public MapModel() {
    }

    public MapModel(double lat, double lng, String placeName, int radius, String todo, String key) {
        this.lat = lat;
        this.lng = lng;
        PlaceName = placeName;
        this.radius = radius;
        this.todo = todo;
        this.key = key;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeString(this.PlaceName);
        dest.writeInt(this.radius);
        dest.writeString(this.todo);
        dest.writeString(this.key);
    }

    protected MapModel(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.PlaceName = in.readString();
        this.radius = in.readInt();
        this.todo = in.readString();
        this.key = in.readString();
    }

    public static final Creator<MapModel> CREATOR = new Creator<MapModel>() {
        @Override
        public MapModel createFromParcel(Parcel source) {
            return new MapModel(source);
        }

        @Override
        public MapModel[] newArray(int size) {
            return new MapModel[size];
        }
    };
}
