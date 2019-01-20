package cc.shrestha.ualberta5star;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BuildingAdapter.OnItemClickListener {
    public static final String EXTRA_BUILDING_ID = "1";
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_SHORT_NAME = "shortName";
    public static final String EXTRA_LONG_NAME = "longName";
    public static final String EXTRA_RATING = "rating";

    private RecyclerView mRecyclerView;
    private BuildingAdapter mBuildingAdapter;
    private ArrayList<BuildingItem> mBuildingList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recylcerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBuildingList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        listBuilding();
    }

    private void listBuilding() {
        String url = "http://www.shrestha.cc/building_api.php/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("building");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int buildingId = hit.getInt("id");
                                String buildingShortName = hit.getString("name");
                                String buildingLongName = hit.getString("shortdesc");
                                String imageUrl = hit.getString("image");
                                double avgRating = hit.getInt("rating");

                                mBuildingList.add(new BuildingItem(buildingId, buildingShortName, buildingLongName, imageUrl, avgRating));
                            }
                            mBuildingAdapter = new BuildingAdapter(MainActivity.this, mBuildingList);
                            mRecyclerView.setAdapter(mBuildingAdapter);
                            mBuildingAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        BuildingItem clickedItem = mBuildingList.get(position);

        detailIntent.putExtra(EXTRA_BUILDING_ID,clickedItem.getId());
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_SHORT_NAME, clickedItem.getShortName());
        detailIntent.putExtra(EXTRA_LONG_NAME, clickedItem.getLongName());
        detailIntent.putExtra(EXTRA_RATING, clickedItem.getRating());

        startActivity(detailIntent);
    }
}