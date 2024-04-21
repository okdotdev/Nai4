import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Iris> readIrisDataFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Iris> data = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String tag = parts[parts.length - 1];
            List<Double> values = new ArrayList<>();
            for (int i = 0; i < parts.length - 1; i++) {
                values.add(Double.parseDouble(parts[i]));
            }

            if (Iris.getNumber_of_attributes() == 0)
                Iris.setNumber_of_attributes(values.size());

            data.add(new Iris(tag, values));
        }

        return data;
    }
}


