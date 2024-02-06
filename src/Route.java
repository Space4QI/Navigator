import java.util.List;
import java.util.Objects;

public class Route implements Comparable<Route> {

    private String id;

    private double distance;

    private int popularity;

    private boolean isFavorite;

    private List<String> locationPoints;

    public Route(String id, double distance, int popularity, boolean isFavorite, List<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(distance, route.distance) == 0 && popularity == route.popularity && isFavorite == route.isFavorite && Objects.equals(id, route.id) && Objects.equals(locationPoints, route.locationPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, popularity, isFavorite, locationPoints);
    }

    @Override
    public int compareTo(Route route) {
        if (this.locationPoints == null && route.locationPoints == null) {
            return 0;
        } else if (this.locationPoints == null) {
            return -1;
        } else if (route.locationPoints == null) {
            return 1;
        }

        int startPoint = this.locationPoints.get(0).compareTo(route.locationPoints.get(0));
        int endPoint = this.locationPoints.get(this.locationPoints.size() - 1).compareTo(route.locationPoints.get(route.locationPoints.size() - 1));

        if (startPoint == endPoint) {
            return Double.compare(this.distance, route.distance);
        } else if (startPoint != 0) {
            return startPoint;
        } else {
            return endPoint;
        }
    }
}
