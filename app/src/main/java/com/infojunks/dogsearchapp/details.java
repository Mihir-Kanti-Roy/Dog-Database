package com.infojunks.dogsearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ListView listView = (ListView) findViewById(R.id.myDetailsView);

        Intent intent = getIntent();
        String message= intent.getStringExtra(MainActivity.MSG);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://dog.ceo/api/breed/"+ message +"/images/random/10";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("message");
                            ArrayList<String> list = new ArrayList<>();

                            if (jsonArray != null) {
                                int len = jsonArray.length();
                                for (int i=0;i<len;i++){
                                    list.add(jsonArray.getString(i));
                                }
                            }
                            Dog adapter=new Dog(details.this,list);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonObjectRequest);

    }
}
