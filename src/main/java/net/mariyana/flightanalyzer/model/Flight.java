package net.mariyana.flightanalyzer.model;

import java.util.Comparator;

public class Flight implements Comparable {

    public static final Comparator<Flight> BY_DISTANCE_ASC = new Comparator<Flight>() {
        @Override
        public int compare(Flight flight, Flight flight2) {
            if (flight.getDistance() < flight2.getDistance())
                return -1;

            if (flight.getDistance() > flight2.getDistance())
                return 1;

            return 0;
        }
    };

//    public static final Comparator<Flight> BY_DISTANCE_DESC = new Comparator<Flight>() {
//        @Override
//        public int compare(Flight flight, Flight flight2) {
//            if (flight.getDistance() > flight2.getDistance())
//                return -1;
//
//            if (flight.getDistance() < flight2.getDistance())
//                return 1;
//
//            return 0;
//        }
//    };

//    public static final Comparator<Flight> BY_DISTANCE_ASC_ID_DESC = new Comparator<Flight>() {
//        @Override
//        public int compare(Flight flight, Flight flight2) {
//            if (flight.getDistance() < flight2.getDistance())
//                return -1;
//
//            if (flight.getDistance() > flight2.getDistance())
//                return 1;
//
//            if (flight.getId() > flight2.getId())
//                return -1;
//
//            if (flight.getId() < flight2.getId())
//                return 1;
//
//            return 0;
//        }
//    };

    private Integer id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private int distance;

    public Flight(Integer id, Airport departureAirport, Airport arrivalAirport, Airline airline) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.distance = gpsToKilometers(
                getDepartureAirport().getLatitude(),
                getDepartureAirport().getLongitude(),
                getArrivalAirport().getLatitude(),
                getArrivalAirport().getLongitude());
    }

    public Integer getId() {
        return id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public Integer getDistance() {
       return distance;
    }

    public static int gpsToKilometers(float departureLat, float departureLong,
                                   float arrivalLat, float arrivalLong) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(arrivalLat - departureLat);
        double dLng = Math.toRadians(arrivalLong - departureLong);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = (Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(departureLat))
                * Math.cos(Math.toRadians(arrivalLat)));
        double c = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        double distance = earthRadius * c;

        return (int) distance;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
