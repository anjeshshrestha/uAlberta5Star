package cc.shrestha.ualberta5star;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static cc.shrestha.ualberta5star.WashroomActivity.EXTRA_NAME;
import static cc.shrestha.ualberta5star.WashroomActivity.EXTRA_RATING;
import static cc.shrestha.ualberta5star.WashroomActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        double rating = intent.getDoubleExtra(EXTRA_RATING, 0.0);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewName = findViewById(R.id.textViewTitle);
        TextView textViewRating = findViewById(R.id.textViewRating);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewName.setText(name);
        textViewRating.setText("Rating: " + rating);

    }
}
