package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India","China","Australia","Portugal","America","NewZealand","India","China","Australia","Portugal","America","NewZealand","India","China","Australia","Portugal","America","NewZealand","India","China","Australia","Portugal","America","NewZealand"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView,countryList);
        simpleList.setAdapter(arrayAdapter);
        TextView showChoice = findViewById(R.id.text3);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity","position"+position);
                showChoice.setText(countryList[position]);
            }
        });
        Button newButton = findViewById(R.id.new_bt);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewActivity.this, InsertionActiivty.class);
                startActivity (intent);
            }
        });
    }
}