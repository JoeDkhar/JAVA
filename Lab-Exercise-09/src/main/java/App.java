import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        WasteService wasteService = new WasteService();
        try {
            wasteService.addWaste(new Waste(1, "Plastic", 5));
            wasteService.getAllWaste().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

