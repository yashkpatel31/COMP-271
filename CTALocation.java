import java.io.File;

/**
 * A superclass for CTA locations. CTA locations are either bus stops or train stations. This superclass
 * captures the common characteristics of these two types of locations: all locations have a name, an address,
 * and a hashtag. The different characteristics (for example, stations may have elevators, bus stops don't)
 * are delegated to two classes that extend this class.
 */
public class CTALocation implements Comparable<CTALocation> {

    private static final String NOT_AVAILABLE_MESSAGE = "Information not available";

    /** The name of the location, e.g., "95th Street" */
    private String name;

    /** The address of the location, e.g. "15 West 95th Street". Eventually we need an Address object here. */
    private String address;

    private double longitude;
    private double latitude;

    /** Hashtag for social media use */
    private String hashTag;

    /**
     * Basic constructor. It assigns a name to a location and sets up a hashtag for it as well.
     *
     * @param name String with name of a location.
     */
    public CTALocation(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = NOT_AVAILABLE_MESSAGE;
        this.hashTag = "#" + name.toLowerCase();
    } // constructor CTALocation

    private double distanceFromDowntown(double latitude, double longitude) {
                return Math.sqrt((Math.pow(longitude + 87.6298057, 2)) + Math.pow(latitude - 41.881866, 2));
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getHashTag() {
        return hashTag;
    }

    /*
State & Madison (Downtown): 41.881866, -87.6298057
 */
    //calculate the distance from the different stop to downtown

    @Override
    public int compareTo(CTALocation other) {
        int d = 0;
        if (d < 0) {
            System.out.println("O'Hare is closer to downtown than Loyola");
        } else if (d > 0) {
            System.out.println("O'Hare is further from downtown than Loyola");
        } else {
            System.out.println("O'Hare is as far from downtown as Loyola");
        }

        double d1 = distanceFromDowntown(this.longitude, this.latitude);
        double d2 = distanceFromDowntown(other.longitude, other.latitude);
        return (int) d2 - (int) d1;
    }
} // class CTALocation