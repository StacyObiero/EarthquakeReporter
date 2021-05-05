package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

//Custom ArrayAdapter
public class SearchAdapter extends ArrayAdapter<EarthquakeItem> {
    //Constructor
    public SearchAdapter(Context context, ArrayList<EarthquakeItem> earthquakeItems) {
        super(context, 0, earthquakeItems);
    }
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        EarthquakeItem earthquakeItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_custom_list, parent, false);
        }
        // Initialize Views
        TextView tvDate = (TextView)  convertView.findViewById(R.id.tvDate);
        EditText et_date = (EditText)  convertView.findViewById(R.id.et_date);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvLatitude = (TextView) convertView.findViewById(R.id.tvLatitude);
        TextView tvLongitude = (TextView) convertView.findViewById(R.id.tvLongitude);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };

        et_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog =new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month =month+1;
                        String date = day+"/"+month+"/"+year;
                        et_date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

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

