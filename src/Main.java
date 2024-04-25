import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "iris.data";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the k value: ");
        int k = scanner.nextInt();



        List<Record> irises = DataReader.readRecordsFromFile(filename);

        KMeans kMeans =  new KMeans(irises, k);
        kMeans.group();


    }

}
