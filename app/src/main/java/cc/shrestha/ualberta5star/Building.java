package cc.shrestha.ualberta5star;

public class Building {
    private int id;
    private String name;
    private String shortdesc;
    private double rating;
    private String image;

    public Building(int id, String name, String shortdesc, double rating, String image) {
        this.id = id;
        this.name = name;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }
    public String getImage() {
        return image;
    }
}