package cc.shrestha.ualberta5star;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

        //initializing the productlist
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
        Log.d("myTag","this should work very much");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject building = array.getJSONObject(i);

                                //Log.d("Stuff",building.get("shortdesc").toString());

                                //adding the product to product list
                                buildingList.add(new Building(
                                        building.getInt("id"),
                                        building.getString("name"),
                                        building.getString("shortdesc"),
                                        building.getDouble("rating"),
                                        building.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            BuildingAdapter adapter = new BuildingAdapter(MainActivity.this, buildingList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}