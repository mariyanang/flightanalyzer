package net.mariyana.flightanalyzer.model;

import java.util.Comparator;

public class Airport implements Comparable {

    public static final String ID_UNKNOWN = "UNKNOWN";
    public static final String ID_SCHIPHOL = "AMS";

    public static final Comparator<Airport> BY_ID_ASC = new Comparator<Airport>() {
        @Override
        public int compare(Airport airport, Airport airport2) {
            return airport.getId().compareTo(airport2.getId());
        }
    };
    public static Comparator<Airport> BY_ID_DESC = new Comparator<Airport>() {
        @Override
        public int compare(Airport airport, Airport airport2) {
            return airport2.getId().compareTo(airport.getId());
        }
    };

    private String id;
    private Float latitude;
    private Float longitude;
    private String name;
    private String city;
    private String countryId;

    public Airport(String id, Float latitude, Float longitude, String name, String city, String countryId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.city = city;
        this.countryId = countryId;
    }

    public String getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountryId() {
        return countryId;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Airport))
            return -1;

        return this.getId().compareTo(((Airport) o).getId());
    }

}
