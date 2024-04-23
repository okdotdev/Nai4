import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<Record> readRecordsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Record> data = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String tag = parts[parts.length - 1];
            List<Double> values = new ArrayList<>();
            for (int i = 0; i < parts.length - 1; i++) {
                values.add(Double.parseDouble(parts[i]));
            }

            if (Record.getNumber_of_attributes() == 0)
                Record.setNumber_of_attributes(values.size());

            data.add(new Record(tag, values));
        }

        return data;
    }


}


