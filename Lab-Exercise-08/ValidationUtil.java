import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValidationUtil {
    private static final String WASTE_ID_REGEX = "^WD-\\d{4}-[A-Z]{2}$";
    private static final String WASTE_TYPE_REGEX = "^(Organic|Plastic|Metal|Electronic|Paper)$";

    public static boolean validateWasteId(String id) {
        return Pattern.matches(WASTE_ID_REGEX, id);
    }

    public static boolean validateWasteType(String type) {
        return Pattern.matches(WASTE_TYPE_REGEX, type);
    }

    public static boolean validateWeight(double weight) {
        return weight > 0 && weight < 1000;
    }
}

