package cc.shrestha.ualberta5star;

public class BuildingItem {
    private int mId;
    private String mShortName;
    private String mLongName;
    private String mImageUrl;
    private double mRating;

    public BuildingItem(int id, String shortName, String longName, String imageUrl, double rating) {
        mId = id;
        mShortName = shortName;
        mLongName = longName;
        mImageUrl = imageUrl;
        mRating = rating;
    }
    public int getId(){
        return mId;
    }
    public String getShortName(){
        return mShortName;
    }
    public String getLongName(){
        return mLongName;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public double getRating() {
        return mRating;
    }
}
