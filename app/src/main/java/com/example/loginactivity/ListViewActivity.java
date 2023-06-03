package com.example.loginactivity;

import androidx.annotation.NonNull;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
      try {
            run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                Intent intent = new Intent(ListViewActivity.this,InsertionActivity.class);
                startActivity (intent);
            }
        });
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewActivity.this,MainActivity.class);
                startActivity (intent);
            }
        });

    }
    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Intent username = getIntent();
        String usernameStr = username.getStringExtra("Username");
        String url = "https://demo.hashup.tech/std/items?std_id="+usernameStr;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
                Log.d("MainActivity", "Request failed");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    JSONObject jsonObj = new JSONObject(response.body().string());
                    JSONArray data = jsonObj.getJSONArray("data");
                    Log.d("MainActivity", "Response received");
                    Log.d("MainActivity", "Parsed JSONObject: " + jsonObj.toString());
                    Log.d("MainActivity", "Parsed JSONArray: " + data.toString());
                } catch (JSONException err) {
                    Log.d("Error",err.toString());
                }
            }
        });
    }

}