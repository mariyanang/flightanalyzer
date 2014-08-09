package net.mariyana.flightanalyzer.model;

import java.util.Comparator;

public class Airline implements Comparable {

    public static final String ID_UNKNOWN = "UNKNOWN";
    public static final String NAME_UNKNOWN = "UNKNOWN";

    public static final Comparator<Airline> BY_DISTANCE_ASC = new Comparator<Airline>() {

        @Override
        public int compare(Airline airline, Airline airline2) {
            if (airline.getMileage() < airline2.getMileage())
                return -1;
            if (airline.getMileage() > airline2.getMileage())
                return 1;

            return 0;
        }
    };

    public static final Comparator<Airline> BY_DISTANCE_ASC_NAME_DESC = new Comparator<Airline>() {

        @Override
        public int compare(Airline airline, Airline airline2) {

            int compare = BY_DISTANCE_ASC.compare(airline, airline2);
            if (compare == 0)
                return airline2.getName().compareTo(airline.getName());

            return compare;
        }
    };

    private String name;
    private String id;
    private float mileage = 0f;

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

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Airline))
            return -2;
        if (this.getMileage() < ((Airline) o).getMileage())
            return -1;
        if (this.getMileage() > ((Airline) o).getMileage())
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
