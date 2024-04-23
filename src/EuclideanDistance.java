public class EuclideanDistance {
     public static double calculate(Record f1, Record f2) {
        double sum = 0;
        for (int i = 0; i < f1.getCoordinates().size(); i++) {
            Double v1 = f1.getCoordinates().get(i);
            Double v2 = f2.getCoordinates().get(i);

            if (v1 != null && v2 != null) {
                sum += Math.pow(v1 - v2, 2);
            }
        }

        return Math.sqrt(sum);
    }
}
