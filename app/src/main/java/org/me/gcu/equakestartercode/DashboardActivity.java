package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends BaseActivity {

    private Button button_list;
    private Button button_magnitude;
    private Button button_search;
    private Button button_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button_list = (Button) findViewById(R.id.button_list);
        button_search = (Button) findViewById(R.id.button_search);
        button_map = (Button) findViewById(R.id.button_map);

        button_list.setOnClickListener(new View.OnClickListener() {


                @Override
                        public void onClick(View v){
                    openActivityList();
                }

        });



        button_search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v){
                openActivitySearch();
            }

        });

        button_map.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v){
                openActivityMap();
            }

        });
    }

    public void openActivityList(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public void openActivitySearch(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openActivityMap(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}