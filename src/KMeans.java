import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans {

    private final List<Record> records;
    private final List<Record> centroids;

    public KMeans(List<Record> records, int k) {
        this.records = records;
        this.centroids = new ArrayList<>();
        initializeCentroids(k);
    }


    public void initializeCentroids(int k) {
        for (int i = 0; i < k; i++) {

            /*
            for (int j = 0; j < Record.getNumber_of_attributes(); j++) {


                double max = Double.MIN_VALUE;
                for (Record record : records) {
                    if (record.getCoordinates().get(j) > max) {
                        max = record.getCoordinates().get(j);
                    }
                }

                double min = Double.MAX_VALUE;
                for (Record record : records) {
                    if (record.getCoordinates().get(j) < min) {
                        min = record.getCoordinates().get(j);
                    }
                }


                double coordinate = Math.random() * (max - min) + min;
                coordinate = Math.round(coordinate * 10.0) / 10.0;
                coordinates.add(coordinate);

            }

             */
            
            int randomIndex = (int) (Math.random() * records.size());
            Record randomRecord = records.get(randomIndex);
            List<Double> coordinates = new ArrayList<>(randomRecord.getCoordinates());

            Record centroid = new Record("CENTROID", coordinates);

            centroids.add(centroid);
        }
    }

    public void group() {

        double previousTotalSquaredDistance = Double.MAX_VALUE;
        int iteration = 0;

        while (true) {
            //znajdź najbliższe centroidy dla każdego rekordu
            double totalSquaredDistance = 0.0;
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
                totalSquaredDistance += Math.pow(minDistance, 2);

            }

            //oblicz nowe współrzędne centroid
            for (Record centroid : centroids) {
                List<Double> newCoordinates = calculateNewCoordinatesOfCentroid(centroid);
                centroid.setCoordinates(newCoordinates);
            }

            System.out.println("Iteracja " + iteration + ": sumę kwadratów odległości = " + totalSquaredDistance);
            iteration++;


            if (Math.abs(totalSquaredDistance - previousTotalSquaredDistance) < 0.0001) {
                break;
            }


            previousTotalSquaredDistance = totalSquaredDistance;
        }


        Map<Record, List<Record>> clusters = new HashMap<>();
        for (Record centroid : centroids) {
            clusters.put(centroid, new ArrayList<>());
        }

        for (Record record : records) {
            clusters.get(record.getClosestCentroid()).add(record);
        }

        for (Map.Entry<Record, List<Record>> entry : clusters.entrySet()) {
            Record centroid = entry.getKey();
            List<Record> cluster = entry.getValue();

            Map<String, Integer> speciesCount = new HashMap<>();
            for (Record record : cluster) {
                String species = record.getClassificationTag();
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }

            double entropy = 0.0;
            int clusterSize = cluster.size();

            for (Map.Entry<String, Integer> speciesEntry : speciesCount.entrySet()) {
                double probability = (double) speciesEntry.getValue() / clusterSize;
                entropy -= probability * Math.log(probability) / Math.log(2);

            }
            System.out.println("--------------------");
            System.out.println("Centroid: " + centroid.getCoordinates());
            System.out.println("Cluster size: " + cluster.size());
            System.out.println("Entropy: " + entropy);

            for (Record record : cluster) {
                System.out.println(record);
            }
        }


    }

    private List<Double> calculateNewCoordinatesOfCentroid(Record centroid) {
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


}
