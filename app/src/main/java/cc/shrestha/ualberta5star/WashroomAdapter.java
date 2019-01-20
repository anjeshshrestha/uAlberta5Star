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


public class WashroomAdapter extends RecyclerView.Adapter<WashroomAdapter.WashroomViewHolder> {
    private Context mContext;
    private ArrayList<WashroomItem> mWashroomList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WashroomAdapter(Context context, ArrayList<WashroomItem> washroomList) {
        mContext = context;
        mWashroomList = washroomList;
    }

    @Override
    public WashroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.washroom_item, parent, false);
        return new WashroomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WashroomViewHolder holder, int position) {
        WashroomItem currentItem = mWashroomList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String name = currentItem.getName();
        String floor = currentItem.getFloor();
        double avgRating = currentItem.getRating();

        holder.mTextViewName.setText(name);
        holder.mTextViewFloor.setText(floor);
        holder.mTextViewRating.setText("Avg Rating: " + avgRating);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mWashroomList.size();
    }

    public class WashroomViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewName, mTextViewFloor, mTextViewRating;

        public WashroomViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextViewName = itemView.findViewById(R.id.textViewTitle);
            mTextViewFloor = itemView.findViewById(R.id.textViewShortDesc);
            mTextViewRating = itemView.findViewById(R.id.textViewRating);

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