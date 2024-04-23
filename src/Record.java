import java.util.List;
import java.util.Objects;

public class Record {
    private final String classificationTag;
    private String prediction;
    private Record closestCentroid;
    private final List<Double> coordinates;
    private static int number_of_attributes = 0;


    public Record(String tag, List<Double> values) {
        this.classificationTag = tag;
        this.coordinates = values;

        if (Objects.equals(tag, "CENTROID")) {
            closestCentroid = this;
        }
    }

    public String getClassificationTag() {
        return classificationTag;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public Record getClosestCentroid() {
        return closestCentroid;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public static int getNumber_of_attributes() {
        return number_of_attributes;
    }

    public static void setNumber_of_attributes(int number_of_attributes) {
        Record.number_of_attributes = number_of_attributes;
    }

    public void setClosestCentroid(Record closestCentroid) {
        this.closestCentroid = closestCentroid;
    }

    public void setCoordinates(List<Double> newCoordinates) {
        coordinates.clear();
        coordinates.addAll(newCoordinates);
    }

    @Override
    public String toString() {
        if (Objects.equals(classificationTag, "CENTROID")) {
            return "Centroid{" +
                    "coordinates=" + coordinates +
                    ",centroidTag=" + closestCentroid.getPrediction() +
                    '}';
        } else {
            return "Record{" +
                    "classificationTag='" + classificationTag + '\'' +
                    ",centroidTag=" + closestCentroid.getPrediction() +
                    '}';
        }


    }


}
