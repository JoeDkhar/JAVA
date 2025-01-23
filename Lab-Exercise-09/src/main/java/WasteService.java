import java.sql.SQLException;
import java.util.List;

public class WasteService {
    private WasteDao wasteDao;

    public WasteService() {
        this.wasteDao = new WasteDao();
    }

    public void addWaste(Waste waste) throws SQLException {
        wasteDao.addWaste(waste);
    }

    public List<Waste> getAllWaste() throws SQLException {
        return wasteDao.getAllWaste();
    }
}