package cc.shrestha.ualberta5star;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static cc.shrestha.ualberta5star.MainActivity.EXTRA_BUILDING_ID;
import static cc.shrestha.ualberta5star.MainActivity.EXTRA_SHORT_NAME;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private WashroomAdapter mWashroomAdapter;
    private ArrayList<WashroomItem> mWashroomList;
    private int mbuildingId;
    private String mshortName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mbuildingId = intent.getIntExtra(EXTRA_BUILDING_ID,0);
        mshortName = intent.getStringExtra(EXTRA_SHORT_NAME);

        mRecyclerView = findViewById(R.id.recylcerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWashroomList = new ArrayList<>();

        listWashroom();
    }

    private void listWashroom() {
        String url = "http://www.shrestha.cc/washroom_api.php/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("washroom");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int washroomId = hit.getInt("id");
                                String washroomName = mshortName + " " + hit.getString("name");
                                String washroomFloor = "Floor: " + hit.getString("floor");
                                int buildingId = hit.getInt("b_id");
                                String imageUrl = hit.getString("image");
                                int avgRating = hit.getInt("rating");

                                if(mbuildingId == buildingId){
                                    mWashroomList.add(new WashroomItem(washroomId, washroomName, washroomFloor, buildingId, imageUrl, avgRating));
                                }
                            }
                            mWashroomAdapter = new WashroomAdapter(DetailActivity.this, mWashroomList);
                            mRecyclerView.setAdapter(mWashroomAdapter);

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
        Volley.newRequestQueue(this).add(request);
    }
}
