package cc.shrestha.ualberta5star;

public class WashroomItem {
    private int mId;
    private String mName;
    private String mFloor;
    private int mbuildingID;
    private String mImageUrl;
    private double mRating;

    public WashroomItem(int id, String name, String floor, int bId, String imageUrl, double rating) {
        mId = id;
        mName = name;
        mFloor = floor;
        mbuildingID = bId;
        mImageUrl = imageUrl;
        mRating = rating;
    }
    public int getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
    public String getFloor(){
        return mFloor;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public int getBuildingId(){return mbuildingID; }
    public double getRating() {
        return mRating;
    }
}
