package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class XmlParser {
    private static final String TAG = "XmlParser";
    private ArrayList<EarthquakeItem> application;
    private ArrayList<LatLng> arrayList;


    public XmlParser() {
        this.application = new ArrayList<>();
    }

    public ArrayList<EarthquakeItem> getApplication() {
        return application;
    }
    public ArrayList<LatLng> getArrayList() {
        return arrayList;
    }
    public boolean parse(String xmlData){
        Log.d(TAG, "parse: called");
        boolean status = true;
        EarthquakeItem currentObject = null;
        boolean inEntry = false;
        String textValue = "";


        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("item".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentObject = new EarthquakeItem();

                        }else { Log.d(TAG,"-----------------No Object---------------");
                        break;}
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry){
                            if("item".equalsIgnoreCase(tagName)){
                                application.add(currentObject);

                            } else if ("title".equalsIgnoreCase(tagName)){

                                String[] parts = textValue.split(":");
                                String part3 = parts[2];

                                currentObject.setTitle(part3);
                            } else if ("description".equalsIgnoreCase(tagName)){

                                String[] parts = textValue.split(";");
                                String part5 = parts[4];
                                String[] field_and_value = parts[4].split(":");
                                float magnitude = Float.parseFloat(field_and_value[1]);


                                currentObject.setDescription(field_and_value[1]);

                            } else if ("link".equalsIgnoreCase(tagName)){

                                currentObject.setLink(textValue);

                            } else if ("pubDate".equalsIgnoreCase(tagName)){

                                currentObject.setPubDate(textValue);

                            } else if ("category".equalsIgnoreCase(tagName)){

                                currentObject.setCategory(textValue);

                            } else if ("lat".equalsIgnoreCase(tagName)){
                                LatLng latLng= new LatLng(0,0);
                                currentObject.setLatitude(Double.parseDouble(textValue));
                                Log.d(TAG, "Double  Parsed ***************");

                            } else if ("long".equalsIgnoreCase(tagName)){


                                currentObject.setLongitude(Double.parseDouble(textValue));


                            }
                        }
                        break;

                    default:

                }
                eventType = xpp.next();

            }

            for(EarthquakeItem app:application){
                Log.d(TAG, "parse:---------------------------------- ");
                Log.d(TAG,  app.toString());
            }
        }catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}