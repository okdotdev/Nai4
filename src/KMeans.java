import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans {

    private final List<Record> records;
    private final List<Record> centroids;
    private final int maxIterations;

    public KMeans(List<Record> irisData, int k, int maxIterations) {
        this.records = irisData;
        this.centroids = new ArrayList<>();
        this.maxIterations = maxIterations;


        for (int i = 0; i < k; i++) {

            List<Double> coordinates = new ArrayList<>();

            for (int j = 0; j < Record.getNumber_of_attributes(); j++) {
                double coordinate = Math.random() * 10;
                coordinate = Math.round(coordinate * 10.0) / 10.0;
                coordinates.add(coordinate);
                //System.out.println("coordinate: " + coordinate);
            }
            Record centroid = new Record("CENTROID", coordinates);

            centroids.add(centroid);
        }

        System.out.println("Initial centroids: ");
        if (centroids.size() > 5)
            System.out.println("Too many centroids to print");
        else {
            for (Record centroid : centroids) {
                System.out.println(centroid);
            }
        }

        System.out.println(" ");

        run();
    }

    public void run() {

        int iteration = 0;
        while (iteration < maxIterations) {
            for (Record record : records) {
                double minDistance = Double.MAX_VALUE;
                Record closestCentroid = null;

                for (Record centroid : centroids) {
                    double distance = EuclideanDistance.calculate(record, centroid);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestCentroid = centroid;
                    }
                }

                record.setClosestCentroid(closestCentroid);
            }

            for (Record centroid : centroids) {
                List<Double> newCoordinates = calculateNewCoordinates(centroid);
                centroid.setCoordinates(newCoordinates);
            }

            iteration++;
        }

        predictTagOfCentroid();
        predictTagOfRecordFromClosestCentroid();

        System.out.println("Final centroids: ");
        if (centroids.size() > 5)
            System.out.println("Too many centroids to print");
        else
            for (Record centroid : centroids) {
                System.out.println(centroid);
            }

        System.out.println("Predictions:");
        for (Record record : records) {
            System.out.println(record);
        }

        System.out.println(" ");
        System.out.println("Correct predictions: " + correctPredictions());
        System.out.println("Total predictions: " + records.size());
        System.out.println("Accuracy: " + (double) correctPredictions() / records.size() * 100 + "%");

    }

    private List<Double> calculateNewCoordinates(Record centroid) {
        List<Double> newCoordinates = new ArrayList<>();
        for (int i = 0; i < Record.getNumber_of_attributes(); i++) {
            double sum = 0;
            int count = 0;
            for (Record record : records) {
                if (record.getClosestCentroid() == centroid) {
                    sum += record.getCoordinates().get(i);
                    count++;
                }
            }
            if (count != 0) {
                newCoordinates.add(sum / count);
            } else {
                newCoordinates.add(centroid.getCoordinates().get(i));
            }
        }
        return newCoordinates;
    }


    private void predictTagOfCentroid() {

        for (Record centroid : centroids) {
            Map<String, Integer> tagCounts = new HashMap<>();
            for (Record record : records) {
                if (record.getClosestCentroid() == centroid) {
                    String tag = record.getClassificationTag();
                    tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                }
            }

            String mostCommonTag = null;
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostCommonTag = entry.getKey();
                    maxCount = entry.getValue();
                }
            }

            centroid.setPrediction(mostCommonTag);
        }
    }


    private void predictTagOfRecordFromClosestCentroid() {
        for (Record record : records) {
            Record closestCentroid = record.getClosestCentroid();
            record.setPrediction(closestCentroid.getPrediction());
        }
    }

    private int correctPredictions() {

        int correctPredictions = 0;
        for (Record record : records) {
            if (record.getClassificationTag().equals(record.getPrediction())) {

                correctPredictions++;
            }
        }
        return correctPredictions;
    }
}
