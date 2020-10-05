package com.example.wallpaerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
WallpaperAdapter wallpaperAdapter;
List<WallpaperModel> wallpaperModelList;
int page=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerview);
        wallpaperModelList=new ArrayList<>();
        wallpaperAdapter=new WallpaperAdapter(this,wallpaperModelList);
        recyclerView.setAdapter(wallpaperAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        fetc();

    }

public  void fetc(){
    StringRequest request =new StringRequest(Request.Method.GET, "https://api.pexels.com/v1/curated/?page"+page+"&per_page=80",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject= new JSONObject(response);
                        JSONArray jsonArray= jsonObject.getJSONArray("photos");
                        int legth=jsonArray.length();
                        for (int i=0;i<legth;i++){


                         JSONObject ojects=jsonArray.getJSONObject(i);
                         int id=ojects.getInt("id");
                         JSONObject objectImaes=ojects.getJSONObject( "src");
                         String originalurl=objectImaes.getString("original");
                            String mediumurl=objectImaes.getString("medium");



                            WallpaperModel wallpaperModel=new WallpaperModel(id,originalurl,mediumurl);
                            wallpaperModelList.add(wallpaperModel);



                        }






                        wallpaperAdapter.notifyDataSetChanged();
                        page++;
                        


                    }catch (JSONException e){


                    }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
    ){

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {

            Map<String,String> params= new HashMap<>();
            params.put("Authorization","563492ad6f9170000100000113f432d565e0421bba3484fa78f7d987");

            return params;
        }
    };

    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
    requestQueue.add(request);


}
}