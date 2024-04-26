public class EuclideanDistance {
    public static double calculate(IVector a, IVector b) {
        double sum = 0;
        for (int i = 0; i < a.getCoordinates().size(); i++) {
            Double v1 = a.getCoordinates().get(i);
            Double v2 = b.getCoordinates().get(i);

            if (v1 != null && v2 != null) {
                sum += Math.pow(v1 - v2, 2);
            }
        }

        return Math.sqrt(sum);
    }
}
