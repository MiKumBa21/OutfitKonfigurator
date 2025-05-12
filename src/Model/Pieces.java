package Model;


public class Pieces {

    private String name;
    private String color;
    private String style;
    private String weather;
    private String season;
    private String imageSource;

    public Pieces(String name, String color, String style, String season, String imageSource) {
        setName(name);
        setColor(color);
        setStyle(style);
        setSeason(season);
        setImageSource(imageSource);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Farbe: " + getColor() + ", Style: " + getStyle() + ", Jahreszeit: " + getSeason() + ", ImageSource: " + getImageSource();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String farbe) {
        this.color = farbe;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
