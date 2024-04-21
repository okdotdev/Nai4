import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class KMeans {

    private final List<Iris> irisData;
    private final List<Iris> centroids;
    private final int k;

    public KMeans(List<Iris> irisData, int k) {
        this.irisData = irisData;
        this.k = k;
        this.centroids = new ArrayList<>();


        for (int i = 0; i < k; i++) {

            List<Double> coordinates = new ArrayList<>();

            for (int j = 0; j < Iris.getNumber_of_attributes(); j++) {
                double coordinate = Math.random() * 10;
                coordinate = Math.round(coordinate * 10.0) / 10.0;
                coordinates.add(coordinate);
                //System.out.println("coordinate: " + coordinate);
            }


            Iris centroid = new Iris("CENTROID", coordinates);

            centroids.add(centroid);
        }

        System.out.println("Initial centroids: ");
        for (Iris centroid : centroids) {
            System.out.println(centroid);
        }

        //print the data

        for (Iris iris : irisData) {
            System.out.println(iris.getCoordinates());
        }

    }


}
