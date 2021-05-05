package org.me.gcu.equakestartercode;


//STACY OBIERO-S1903347

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import android.util.Log;
import java.lang.*;
import java.util.stream.Stream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String TAG = "MainActivity";

    XmlParser xmlParser = new XmlParser();
    MainActivity mainActivity = new MainActivity();
    EarthquakeItem earthquakeItem = new EarthquakeItem();
    LatLng latLng = new LatLng(0,0);
    ArrayList<EarthquakeItem> quakeList = xmlParser.getApplication();

   // double latitude = quakeList.get(0).getLatitude();



    List<LatLng> arrayList = new ArrayList<>();

//List<Double> lats = quakeList.stream().map(EarthquakeItem::getLatitude).collect(Collectors.toList());
    // List<Double> longs = quakeList.stream().map(EarthquakeItem::getLongitude).collect(Collectors.toList());
    //ArrayList<Object> newList = new ArrayList<>() ;
   // {
//for (int i = 0 ; i<oldList.size();i++){
       // newList.add(oldList.get(i)) ;
   // }}


   // }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
for (int i=0;i<arrayList.size();i++){
       //LatLng mCurrentPlace= new LatLng(quakeList.get(i).getLatitude(),quakeList.get(i).getLongitude());
    //LatLng v = quakeList.get(i).getLatitude(),quakeList.get(i).getLongitude();

}


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this::onMapReady);
        LatLng Nairobi = new LatLng(-1.2,36.83333);


        for (int i=0; i<quakeList.size();i++){

            LatLng mCurrentPlace = new LatLng(quakeList.get(i).getLatitude(),quakeList.get(i).getLongitude());
            arrayList.add(new LatLng(quakeList.get(i).getLatitude(),quakeList.get(i).getLongitude()));
            //arrayList.add(mCurrentPlace);
            Log.d(TAG,"***********************"+ "Markers Added");
        }


        arrayList.add(new LatLng(-1.2,36.83333));
        arrayList.add(new LatLng(54.677,-3.314));
        arrayList.add(new LatLng(56.282,-3.756));
        arrayList.add(new LatLng(53.471,-3.711));
        arrayList.add(new LatLng(53.456,-3.748));
        arrayList.add(new LatLng(54.111,-1.103));
        arrayList.add(new LatLng(58.312,-4.616));
        arrayList.add(new LatLng(51.838,-3.167));



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
           mMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();
for (int i=0;i<arrayList.size();i++){
    mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
   // markerOptions.position(new LatLng(earthquakeItem.getLatitude(), earthquakeItem.getLongitude()));

    //Zoom map
    mMap.animateCamera(CameraUpdateFactory.zoomTo(25.0f));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));

}


    }
}

