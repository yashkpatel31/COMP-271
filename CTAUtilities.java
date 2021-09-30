

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CTAUtilities {

    // ohare longitude and latitude = (41.97766526°, -87.90422307°)
    // loyola longitude and latitude = (42.001073°, -87.661061°)

    public static Scanner fileScanner(String filename) {
        Scanner sc;
        File dataFile = new File(filename);
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e){
            e.printStackTrace(); // to print the error is any
            sc = null;
        }
        return sc;
    } // method fileScanner

    public static void main(String[] args) {
//        ArrayList<CTAStation> location = loadStations("CTA/src/station.csv");
//        for(CTAStation cookie : location){
//            System.out.printf("%s: %f, %f\n", cookie.getName(), cookie.getLongitude(), cookie.getLatitude());
//        }

        CTALocation ohare = new CTALocation ("ohare", 41.97766526, -87.90422307);
        CTALocation loyola = new CTALocation ("loyola", 42.001073, -87.661061);

        int d = ohare.compareTo(loyola);
/*
        if (d < 0) {
            System.out.println("O'Hare is closer to downtown than Loyola");
        } else if (d > 0) {
            System.out.println("O'Hare is further from downtown than Loyola");
        } else {
            System.out.println("O'Hare is as far from downtown as Loyola");
        }
*/
    }

    public static ArrayList<CTAStation> loadStations(String filename) {
        ArrayList<CTAStation> stations = new ArrayList<>();
        Scanner s = fileScanner(filename);
        s.nextLine(); // Call nextLine() to skip over the CSV header
        while (s.hasNextLine()) {
            // Splits by all commas except those with trailing whitespace
            String[] entry = s.nextLine().split("(,(?=\\S))");
            double[] location = parseLocation(entry[16]); // Returns double[] with longitude and latitude
            stations.add(new CTAStation(entry[3], location[0], location[1]));
        }
        return stations;
    } // method loadStation;

    private static double[] parseLocation(String location) {
        String[] parts = location.replaceAll("[^\\d.,-]*", "").split(",");
        return new double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
    }

}