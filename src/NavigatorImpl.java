import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class NavigatorImpl implements Navigator {

    private MyTreeSet<Route> routes;


    public NavigatorImpl() {
        this.routes = new MyTreeSet<>();
    }

    @Override
    public void addRoute(Route route) {
        if (!isRouteExist(route)) {
            Node<Route> node = new Node<>(route);
            routes.add(node.value);
            System.out.println("Маршрут успешно добавлен");
        } else {
            System.out.println("Маршрут с такими данными уже существует");
        }
    }

    @Override
    public void removeRoute(String routeId) {
        for (Route route : routes) {
            if (route.getId().equals(routeId)) {
                Node<Route> node = new Node<>(route);
                routes.delete(node.value);
                return;
            }
        }
    }

    @Override
    public boolean contains(Route route) {
        return false;
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (Route route : routes) {
            if (route != null && route.getId().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void showAllRoutes() {
        if (routes.isEmpty()) {
            System.out.println("Маршрутов не найдено");
        } else {
            System.out.println("Список всех маршрутов");
            for (Route route : routes) {
                System.out.println(
                        route.getId() + ": " +
                                "Расстояние: " + route.getDistance() + " " +
                                "Популярность: " + route.getPopularity() + " " +
                                "Маршрут избранный: " + route.isFavorite() + " " +
                                "Точки маршрута: " + route.getLocationPoints());
            }
        }
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route != null) {
            route.setPopularity(route.getPopularity() + 1);
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyTreeSet<Route> result = new MyTreeSet<>(Comparator.<Route, Boolean>comparing(Route::isFavorite)
                .thenComparingDouble(Route::getDistance)
                .thenComparingInt(Route::getPopularity).reversed()
                .thenComparing(Comparator.comparingInt(route -> route.getLocationPoints().size())));

        for (Route route : routes) {
            List<String> locationPoints = route.getLocationPoints();
            if (locationPoints.contains(startPoint) && locationPoints.contains(endPoint)) {
                result.add(route);
            }
        }

        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyTreeSet<Route> result = new MyTreeSet<>(Comparator.<Route, Double>comparing(Route::getDistance).reversed()
                .thenComparingInt(Route::getPopularity)
                .reversed());
        boolean routesAdded = false;
        System.out.println("Избранные маршруты");
        for (Route route : routes) {
            if (route.isFavorite() && !route.getLocationPoints().get(0).equals(destinationPoint) && route.getLocationPoints().contains(destinationPoint)) {
                result.add(route);
                routesAdded = true;
            }
        }
        if (routesAdded) {
            result.sort(Comparator.<Route, Double>comparing(Route::getDistance).reversed()
                    .thenComparingInt(Route::getPopularity)
                    .reversed());
        } else {
            System.out.println("Нет маршрутов через точку " + destinationPoint);
        }
        return result;
    }


    @Override
    public Iterable<Route> getTop3Routes() {
        MyTreeSet<Route> result = new MyTreeSet<>(Comparator.<Route, Integer>comparing(Route::getPopularity).reversed()
                .thenComparingDouble(Route::getDistance)
                .thenComparingInt(route -> route.getLocationPoints().size()));
        System.out.println("Топ 5 маршрутов:");
        for (Route route : routes) {
            result.add(route);
        }
        if (result.isEmpty()) {
            System.out.println("Нет маршрутов подходящих под данную сортировку");
            return result;
        }
        MyTreeSet<Route> top = new MyTreeSet<>(Comparator.<Route, Integer>comparing(Route::getPopularity).reversed()
                .thenComparingDouble(Route::getDistance)
                .thenComparingInt(route -> route.getLocationPoints().size()));
        Iterator<Route> iterator = result.iterator();
        int count = 0;
        while (iterator.hasNext() && count < 5) {
            top.add(iterator.next());
            count++;
        }
        return top;
    }


    private boolean isRouteExist(Route route) {
        for (Route r : routes) {
            if (r.getDistance() == route.getDistance() &&
                    r.getLocationPoints().get(0).equals(route.getLocationPoints().get(0)) &&
                    r.getLocationPoints().get(r.getLocationPoints().size() - 1).equals(route.getLocationPoints().get(route.getLocationPoints().size() - 1))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void informationRoute(Route route) {
        if (route != null) {
            System.out.println(route.getId());
            System.out.println("Расстояние: " + route.getDistance());
            System.out.println("Популярность: " + route.getPopularity());
            System.out.println("Маршрут избранный: " + route.isFavorite());
            System.out.println("Точки маршрута: " + route.getLocationPoints());
        } else {
            System.out.println("Маршрут не найден");
        }
    }
}
