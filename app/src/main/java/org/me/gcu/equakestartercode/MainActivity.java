package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private Button button_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_details = (Button) findViewById(R.id.button_details);
      /* button_details.setOnClickListener(new View.OnClickListener() {

Handler detailsHandler = new Handler();
            @Override
            public void onClick(View v){
                Runnable detailsRunnable = new Runnable() {
                    @Override
                    public void run() {
                        openActivityDetails();
                    }
                };
new Thread(detailsRunnable).start();
            }
            public void openActivityDetails(){
                Intent intent = new Intent(MainActivity.this, QuakeDetailsActivity.class);

                detailsHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        startActivity(intent);
                    }
                });
            }

        });*/

        //Get Reference from XML Layout
        //Create XML downloader object and download it from URL Source
        DownloadXML downloadXML = new DownloadXML();
        downloadXML.execute("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");
    }

    private class DownloadXML extends AsyncTask<String,Void,String>{


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: "+s);

            //Create XML Pull-Parser object
            XmlParser xmlParser = new XmlParser();
            xmlParser.parse(s);


           //List<EarthquakeItem> quakes_list = xmlParser.getApplication();

            //Create ArrayAdapter from EarthquakeItems Array List
            //ArrayAdapter<EarthquakeItem> arrayAdapter = new ArrayAdapter<>(MainActivity.this,R.layout.list_view,xmlParser.getApplication());
          //listEarthquakes.setAdapter(arrayAdapter);

            // Construct the data source
            //ArrayList<EarthquakeItem> arrayOfQuakes = new ArrayList<EarthquakeItem>();

            Handler adapterHandler = new Handler();
            Runnable myRunnable = new Runnable(){
                @Override
                public void run(){

                    ArrayList<EarthquakeItem> arrayOfQuakes = xmlParser.getApplication();
                    // Create the adapter to convert the array to views
                    QuakesAdapter adapter = new QuakesAdapter(MainActivity.this, arrayOfQuakes);


adapterHandler.post(new Runnable() {
    @Override
    public void run() {
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listViews);
        listView.setAdapter(adapter);
    }
});
                }
            };
            new Thread(myRunnable).start();

            /*ArrayList<EarthquakeItem> arrayOfQuakes = xmlParser.getApplication();
            // Create the adapter to convert the array to views
            QuakesAdapter adapter = new QuakesAdapter(MainActivity.this, arrayOfQuakes);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.listViews);
            listView.setAdapter(adapter);*/




            //button_details = (Button) findViewById(R.id.button_details);
           // Intent intent = new Intent(MainActivity.this,QuakeDetailsActivity.class);
            //startActivity(intent);

        }

        @Override
        protected String doInBackground(String... strings) {

            String rssFeed = downloadXML(strings[0]);
            if(rssFeed==null){
                Log.e(TAG, "doInBackground: error downloading" );
            }
            return rssFeed;
        }


        private String downloadXML(String urlAdd){
            StringBuilder xml = new StringBuilder();
            try {
                URL url = new URL(urlAdd);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int count;
                char[] inputs = new char[1000];
                while (true){
                    count = bufferedReader.read(inputs);
                    if (count<0){
                        break;
                    }
                    if(count>0){
                        xml.append(String.copyValueOf(inputs,0,count));
                    }

                }
                bufferedReader.close();
                return xml.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadXML: Invalid url" + e.getMessage());
            }catch (IOException e){
                Log.e(TAG, "downloadXML: IOException" +e.getMessage());
            }
            return null;
        }
    }

    public void startThread(View view){
       Thread2 thread2 = new Thread2(10) ;
       thread2.start();
    }

    public void stopThread(View view){

    }
    class Thread2 extends Thread{
        int seconds;
        Thread2(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run() {
           for (int i=0; i<seconds; i++){
               Log.d(TAG,"startThread" + i);
               try{
                   Thread.sleep(1000);
               } catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        }
    }
}




