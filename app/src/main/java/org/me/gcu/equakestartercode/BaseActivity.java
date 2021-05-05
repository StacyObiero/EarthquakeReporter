package org.me.gcu.equakestartercode;

//STACY OBIERO-S1903347
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

           getMenuInflater().inflate(R.menu.commonmenus,menu);
            return super.onCreateOptionsMenu(menu);
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
         if(id==R.id.dashboard){
             Intent intent = new Intent(BaseActivity.this,DashboardActivity.class);
             startActivity(intent);
             return true;
         } else if(id==R.id.quakes_list){
             Intent intent = new Intent(BaseActivity.this,MainActivity.class);
             startActivity(intent);
             return true;
         }
           else if(id==R.id.search){
             Intent intent = new Intent(BaseActivity.this,SearchActivity.class);
             startActivity(intent);
               return true;
    }
         else if(id==R.id.mapview){
             Intent intent = new Intent(BaseActivity.this,MapActivity.class);
             startActivity(intent);
             return true;
         }


        return super.onOptionsItemSelected(item);
    }
}