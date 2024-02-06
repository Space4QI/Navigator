public interface Navigator {

    void addRoute(Route route);

    void removeRoute(String routeId);

    boolean contains(Route route);

    void showAllRoutes();

    int size();

    Route getRoute(String routeId);

    void chooseRoute(String routeId);

    Iterable<Route>searchRoutes(String startPoint, String endPoint);

    Iterable<Route>getFavoriteRoutes(String destinationPoint);

    Iterable<Route>getTop3Routes();

    void informationRoute(Route route);

}
