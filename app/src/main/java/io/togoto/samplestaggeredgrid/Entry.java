package io.togoto.samplestaggeredgrid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GT
 */
public class Entry {

    private int id;
    private String heading;
    private List<String> points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }

    public void addPoint(String p){
        if(points == null) points = new ArrayList<>();
        points.add(p);
    }
}
