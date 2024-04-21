import java.util.List;

public class Observation {
    String tag = "";
    List<Double> values;

    public Observation(String tag, List<Double> values) {
        this.tag = tag;
        this.values = values;
    }
}
