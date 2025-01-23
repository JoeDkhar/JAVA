import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WasteDao {
    private final String url = "jdbc:mysql://localhost:3306/smartwastes";
    private final String user = "root";
    private final String password = "mit0chr0Ndri@";

    public WasteDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addWaste(Waste waste) throws SQLException {
        String query = "INSERT INTO waste (id, type, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, waste.getId());
            stmt.setString(2, waste.getType());
            stmt.setInt(3, waste.getQuantity());
            stmt.executeUpdate();
        }
    }

    public List<Waste> getAllWaste() throws SQLException {
        List<Waste> wasteList = new ArrayList<>();
        String query = "SELECT * FROM waste";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Waste waste = new Waste(rs.getInt("id"), rs.getString("type"), rs.getInt("quantity"));
                wasteList.add(waste);
            }
        }
        return wasteList;
    }
}