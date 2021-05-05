package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        //Get Reference from XML Layout
        //Create XML downloader object and download it from URL Source
        SearchActivity.DownloadXML downloadXML = new SearchActivity.DownloadXML();
        downloadXML.execute("http://quakes.bgs.ac.uk/feeds/MhSeismology.xml");
    }

    private class DownloadXML extends AsyncTask<String,Void,String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: "+s);

            //Create XML Pull-Parser object
            XmlParser xmlParser = new XmlParser();
            xmlParser.parse(s);


            Handler adapterHandler2 = new Handler();
            Runnable myRunnable2 = new Runnable(){
                @Override
                public void run(){

                    ArrayList<EarthquakeItem> arrayOfQuakes = xmlParser.getApplication();
                    // Create the adapter to convert the array to views
                    SearchAdapter adapter = new SearchAdapter(SearchActivity.this, arrayOfQuakes);


                    adapterHandler2.post(new Runnable() {
                        @Override
                        public void run() {
                            // Attach the adapter to a ListView
                            ListView listView = (ListView) findViewById(R.id.listViews2);
                            listView.setAdapter(adapter);
                        }
                    });
                }
            };
            new Thread(myRunnable2).start();

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



}