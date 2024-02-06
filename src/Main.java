import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Navigator navigator = new NavigatorImpl();

        Route route1 = new Route("1", 100.0, 3, true, Arrays.asList("CityA", "CityB", "CityC"));
        Route route2 = new Route("2", 150.0, 5, false, Arrays.asList("CityA", "CityD", "CityE", "CityF", "CityC"));
        Route route3 = new Route("3", 80.0, 2, true, Arrays.asList("CityB", "CityG"));
        Route route4 = new Route("4", 200.0, 4, true, Arrays.asList("CityD", "CityH", "CityI", "CityJ"));
        Route route5 = new Route("5", 100.0, 3, true, Arrays.asList("CityA", "CityK"));
        Route route6 = new Route("6", 200.0, 5, true, Arrays.asList("CityA", "CityH", "CityC"));

        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);
        navigator.addRoute(route6);

        while (true) {
            try {
                System.out.println("\nВыберите действие:");
                System.out.println("1. Добавить маршрут");
                System.out.println("2. Удалить маршрут");
                System.out.println("3. Показать все маршруты");
                System.out.println("4. Поиск маршрутов");
                System.out.println("5. Избранные маршруты");
                System.out.println("6. Топ-5 маршрутов");
                System.out.println("7. Поиск маршрута по id");
                System.out.println("8. Общее кол-во маршрутов");
                System.out.println("9. Выйти");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addRoute(scanner, navigator);
                        break;
                    case 2:
                        removeRoute(scanner, navigator);
                        break;
                    case 3:
                        showAllRoutes(navigator);
                        break;
                    case 4:
                        searchRoutes(scanner, navigator);
                        break;
                    case 5:
                        showFavoriteRoutes(scanner, navigator);
                        break;
                    case 6:
                        showTopRoutes(navigator);
                        break;
                    case 7:
                        searchRouteById(scanner, navigator);
                        break;
                    case 8:
                        sizeRoute(navigator);
                    case 9:
                        System.out.println("Программа завершена.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Некорректный ввод. Пожалуйста, выберите действие от 1 до 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите корректное значение.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    public static void addRoute(Scanner scanner, Navigator navigator) {
        System.out.println("Введите идентификатор маршрута");
        String id = scanner.nextLine();
        boolean routeExists = navigator.getRoute(id) != null;
        if (routeExists) {
            System.out.println("Маршрут с таким идентификатором уже существует.");
            scanner.nextLine();
        }
        System.out.println("Вы ввели идентификатор маршрута: " + id);

        System.out.println("Введите расстояние маршрута");
        if (!scanner.hasNextDouble()) {
            System.out.println("Ошибка: вы ввели не число");
            scanner.nextLine();
        }
        double distance = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Вы ввели расстояние маршрута: " + distance);

        System.out.println("Введите популярность маршрута");
        if (!scanner.hasNextInt()) {
            System.out.println("Ошибка: вы ввели не число");
            scanner.nextLine();
        }
        int popularity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Вы ввели популярность маршрута: " + popularity);

        System.out.println("Является ли маршрут избранным ? (true/false)");
        if (!scanner.hasNextBoolean()) {
            System.out.println("Ошибка: введите true или false");
            scanner.nextLine();
        }
        boolean isFavorite = scanner.nextBoolean();
        scanner.nextLine();
        System.out.println("Маршрут избранный: " + isFavorite);

        System.out.println("Введите количество точек маршрута");
        int numPoints = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Вы ввели количество точек маршрута: " + numPoints);
        List<String> locationPoints = new ArrayList<>();
        for (int i = 0; i < numPoints; i++) {
            System.out.println("Введите точку маршрута #" + (i + 1));
            String point = scanner.nextLine();
            locationPoints.add(point);
        }
        Route route = new Route(id, distance, popularity, isFavorite, locationPoints);
        navigator.addRoute(route);
    }

    public static void removeRoute(Scanner scanner, Navigator navigator) {
        System.out.println("Введите id для удаления маршрута");
        String routeId = scanner.nextLine();
        boolean routeExists = navigator.getRoute(routeId) != null;
        if (routeExists) {
            navigator.removeRoute(routeId);
            System.out.println("Маршрут успешно удалён");
        } else {
            System.out.println("Марщрута с таким id не существует");
        }
    }

    public static void showAllRoutes(Navigator navigator) {
        navigator.showAllRoutes();
    }

    public static void searchRoutes(Scanner scanner, Navigator navigator) {
        System.out.println("Введите начальную точку");
        String startPoint = scanner.nextLine();
        System.out.println("Введите конечную точку");
        String endPoint = scanner.nextLine();
        System.out.println("Результат");
        Iterable<Route> search = navigator.searchRoutes(startPoint, endPoint);
        for (Route route : search) {
            navigator.informationRoute(route);
        }
    }

    public static void showFavoriteRoutes(Scanner scanner, Navigator navigator) {
        System.out.println("Введите точку");
        String destinationPoint = scanner.nextLine();
        Iterable<Route> favorite = navigator.getFavoriteRoutes(destinationPoint);
        for (Route route : favorite) {
            navigator.informationRoute(route);
        }
    }

    public static void showTopRoutes(Navigator navigator) {
        Iterable<Route> topRoutes = navigator.getTop3Routes();
        for (Route route : topRoutes) {
            navigator.informationRoute(route);
        }
    }

    public static void searchRouteById(Scanner scanner, Navigator navigator) {
        System.out.println("Введите id маршрута, который хотите получить");
        String routeId = scanner.nextLine();
        navigator.chooseRoute(routeId);
        Route route = navigator.getRoute(routeId);
        navigator.informationRoute(route);
    }

    public static void sizeRoute(Navigator navigator){
        int count = navigator.size();
        System.out.println("Общее кол-во маршрутов: " + count);
    }
}
