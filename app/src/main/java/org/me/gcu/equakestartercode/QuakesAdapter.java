package org.me.gcu.equakestartercode;


//STACY OBIERO-S1903347

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Custom ArrayAdapter
public class QuakesAdapter extends ArrayAdapter<EarthquakeItem> {
//Constructor
    public QuakesAdapter(Context context, ArrayList<EarthquakeItem> earthquakeItems) {
        super(context, 0, earthquakeItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EarthquakeItem earthquakeItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }
        // Initialize TextViews
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvLatitude = (TextView) convertView.findViewById(R.id.tvLatitude);
        TextView tvLongitude = (TextView) convertView.findViewById(R.id.tvLongitude);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);

        // Populate data into the custom view
        tvTitle.setText(earthquakeItem.getTitle());
        tvLatitude.setText(earthquakeItem.getLatitude().toString());
        tvLongitude.setText(earthquakeItem.getLongitude().toString());
        tvDescription.setText(earthquakeItem.getDescription());
       Double mag =Double.parseDouble(earthquakeItem.getDescription());

        if (mag>1) {
            tvDescription.setTextColor(Color.WHITE);
            tvDescription.setBackgroundColor(Color.parseColor("#D32F2F"));
        }
        else {
            tvDescription.setTextColor(Color.WHITE);
            tvDescription.setBackgroundColor(Color.parseColor("#00695C"));
        }
        // Return final view to render on screen
        return convertView;
    }


}
