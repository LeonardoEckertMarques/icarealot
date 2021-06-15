package com.example.icarealot.model;

public class Geo {

  double lat;
  double lng;

  public Geo(double latitude, double longitude) {
    this.lat = latitude;
    this.lng = lng;
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

  public Geo() {

  }

}
