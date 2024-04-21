import java.util.List;

public class Iris {
    private String tag = "";
    private final List<Double> coordinates;
    private static int number_of_attributes = 0;


    public Iris(String tag, List<Double> values) {
        this.tag = tag;
        this.coordinates = values;
    }

    public String getTag() {
        return tag;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public static int getNumber_of_attributes() {
        return number_of_attributes;
    }

    public static void setNumber_of_attributes(int number_of_attributes) {
        Iris.number_of_attributes = number_of_attributes;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "tag='" + tag + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
