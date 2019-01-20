package cc.shrestha.ualberta5star;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Building> buildingList;

    public BuildingAdapter(Context mCtx, List<Building> buildingList) {
        this.mCtx = mCtx;
        this.buildingList = buildingList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.building_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Building building = buildingList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(building.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(building.getName());
        holder.textViewShortDesc.setText(building.getShortdesc());
        holder.textViewRating.setText(String.valueOf(building.getRating()));
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}