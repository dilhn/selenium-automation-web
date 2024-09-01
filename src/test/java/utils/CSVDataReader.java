package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVDataReader {

    public static Map<String, String> readCSVData(String filePath) throws IOException {
        Map<String, String> data = new HashMap<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header row
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                // Split each line by comma to get key-value pairs
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    data.put(fields[0], fields[1]);
                }
            }
        }
        return data;
    }
}
