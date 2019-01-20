package cc.shrestha.ualberta5star;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Building> buildingList;
    RecyclerView recyclerView;
    private static final String URL_PRODUCTS = "http://www.shrestha.cc/building_api.php/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the buildingList
        buildingList = new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadBuildings();
    }

    private void loadBuildings() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_PRODUCTS, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Stuff","working");
                    JSONArray jsonArray = response.getJSONArray("");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("Stuff","working");
                        //getting product object from json array
                        JSONObject building = jsonArray.getJSONObject(i);

                        int id = building.getInt("id");
                        String name = building.getString("name");
                        String shortdesc = building.getString("shortdesc");
                        double rating = building.getDouble("rating");
                        String image = building.getString("image");
                        //adding the product to product list
                        buildingList.add(new Building(id, name, shortdesc,rating,image));
                    }

                    //creating adapter object and setting it to recyclerview
                    BuildingAdapter adapter = new BuildingAdapter(MainActivity.this, buildingList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Stuff","not working");
                error.printStackTrace();
            }
        });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(request);

    }
}