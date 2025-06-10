package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Pieces {

    private String name;
    private String color;
    private String style;
    private String type;
    private ArrayList<String> weather;
    private ArrayList<String> season;
    private String imageSource;

    public Pieces(String name, String color, String style, String type, ArrayList<String> weather, ArrayList<String> season, String imageSource) {
        setName(name);
        setColor(color);
        setStyle(style);
        setType(type);
        setWeather(weather);
        setSeason(season);
        setImageSource(imageSource);
    }

    public ImageView getImageView() {
        ImageView view = new ImageView(new Image("File:" + getImageSource()));
        view.setFitHeight(100);
        view.setPreserveRatio(true);
     return view;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Farbe: " + getColor() + ", Style: " + getStyle() + "Wetter: " + printWeather() + ", Jahreszeit: " + printSeason() + ", ImageSource: " + getImageSource();
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

    public ArrayList<String> getSeason() {
        return season;
    }

    public void setSeason(ArrayList<String> season) {
        this.season = season;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<String> weather) {
        this.weather = weather;
    }

    public String printWeather() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < weather.size(); i++) {
            sb.append(weather.get(i));
        }
        return sb.toString();
    }

    public String printSeason() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < season.size(); i++) {
            sb.append(season.get(i));
        }
        return sb.toString();
    }
}
