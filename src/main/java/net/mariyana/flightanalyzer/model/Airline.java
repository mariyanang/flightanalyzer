package net.mariyana.flightanalyzer.model;

import java.util.Comparator;

public class Airline implements Comparable {

    public static final String ID_UNKNOWN = "UNKNOWN";
    public static final String NAME_UNKNOWN = "UNKNOWN";

    public static final Comparator<Airline> BY_DISTANCE_ASC = new Comparator<Airline>() {

        @Override
        public int compare(Airline airline, Airline airline2) {
            if (airline.getDistanceOfAirlineFLights() < airline2.getDistanceOfAirlineFLights())
                return -1;
            if (airline.getDistanceOfAirlineFLights() > airline2.getDistanceOfAirlineFLights())
                return 1;

            return 0;
        }
    };

    private String name;
    private String id;

    public Airline(String airlineId, String name) {
        this.id = airlineId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDistanceOfAirlineFLights() {

        return 0;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Airline))
            return -2;
        if (this.getDistanceOfAirlineFLights() < ((Airline) o).getDistanceOfAirlineFLights())
            return -1;
        if (this.getDistanceOfAirlineFLights() > ((Airline) o).getDistanceOfAirlineFLights())
            return 1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Airline))
            return false;

        return this.getId().equals(((Airline) o).getId());
    }

    @Override
    public int hashCode() {
        int prime1 = 31;
        int prime2 = 17;
        return prime1 * prime2 + this.getId().hashCode();
    }
}
