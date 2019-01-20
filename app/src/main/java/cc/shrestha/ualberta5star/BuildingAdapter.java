package cc.shrestha.ualberta5star;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder> {
    private Context mContext;
    private ArrayList<BuildingItem> mBuildingList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public BuildingAdapter(Context context, ArrayList<BuildingItem> buildingList) {
        mContext = context;
        mBuildingList = buildingList;
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.building_item, parent, false);
        return new BuildingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BuildingViewHolder holder, int position) {
        BuildingItem currentItem = mBuildingList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String buildingShortName = currentItem.getShortName();
        String buildingLongName = currentItem.getLongName();
        double avgRating = currentItem.getRating();

        holder.mTextViewShortName.setText(buildingShortName);
        holder.mTextViewLongName.setText(buildingLongName);
        holder.mTextViewRating.setText("Avg Rating: " + avgRating);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBuildingList.size();
    }

    public class BuildingViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewShortName, mTextViewLongName, mTextViewRating;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextViewShortName = itemView.findViewById(R.id.textBuildingShortName);
            mTextViewLongName = itemView.findViewById(R.id.textBuildingLongName);
            mTextViewRating = itemView.findViewById(R.id.textAvgRating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
