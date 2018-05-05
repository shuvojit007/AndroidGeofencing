package com.example.shuvojit.shuvojit_geofences.Implementaion.Model;

public class MapModel02 {
    private double lat;
    private double lng;
    private String PlaceName;
    private int radius;

    private String todo;
    private String key;

    public MapModel02(double lat, double lng, String placeName, int radius, String todo, String key) {
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

    public double getLng() {
        return lng;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public int getRadius() {
        return radius;
    }

    public String getTodo() {
        return todo;
    }

    public String getKey() {
        return key;
    }
}
