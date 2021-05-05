package org.me.gcu.equakestartercode;


//STACY OBIERO-S1903347



public class LatLng  {
    private String title;
    private com.google.android.gms.maps.model.LatLng latitude, longitude;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.google.android.gms.maps.model.LatLng getLatitude() {
        return latitude;
    }

    public void setLatitude(com.google.android.gms.maps.model.LatLng latitude) {
        this.latitude = latitude;
    }

    public com.google.android.gms.maps.model.LatLng getLongitude() {
        return longitude;
    }

    public void setLongitude(com.google.android.gms.maps.model.LatLng longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {

        //return " Longitude= "+ longitude + "\n Latitude= " + latitude + "\n Strength= " + category + '\''+"\n\n";
        return  latitude + ","  + longitude;

    }

}

