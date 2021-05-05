package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347

import com.google.android.gms.maps.model.LatLng;

public class EarthquakeItem {
    private String title, description, link, pubDate, category;
    private Double latitude, longitude;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {

        //return " Longitude= "+ longitude + "\n Latitude= " + latitude + "\n Strength= " + category + '\''+"\n\n";
        return title + " \n Longitude= "+ longitude + "\n Latitude= " + latitude + "\n Magnitude= " + description + '\''+"\n\n";

    }

}