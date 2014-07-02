package net.mariyana.flightanalyzer;

import net.mariyana.flightanalyzer.model.Airline;
import net.mariyana.flightanalyzer.model.Airport;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The @FlightAnalyzer interface provides the business methods as required by the assignment.
 */

public interface FlightAnalyzer {
    /**
     * Configures the analyzer to return results from the given input stream.
     * The input stream format for each line looks like:
     * "airline_id","flight_number","departure_airport_id","arrival_airport_id"
     *
     * @param inputStream
     */
    void configure(InputStream inputStream);

    /**
     * Reads configured input stream and returns the number of flights which have
     * departed from Schiphol airport
     *
     * @return totalFlights
     */
    int getTotalDepartureFlightsFromSchiphol() throws IOException;

    /**
     * Reads the given input stream and
     * returns the number of airports reachable from Schiphol airport
     *
     * @return airportsReachableFromSchiphol
     */
    int getTotalAirportsReachableFromSchiphol() throws IOException;

    /**
     * Reads the given input stream and
     * returns the number of airlines flying from Schiphol airport
     *
     * @return airlinesFlyingFromSchiphol
     */
    int getTotalAirlinesFlyingFromSchiphol() throws IOException;

    /**
     * Reads the given input stream and
     * returns the average distance of flights
     * from Schiphol airport in kilometers
     *
     * @return averageDistanceOfFlights
     */
    int getAverageDistanceOfFlightsFromSchiphol() throws IOException;

    /**
     * Reads the given input stream and
     * returns a string for every airports that
     * could be reached directly from Schiphol
     * Airport that day, sorted by the distance
     * from Schiphol Airport, ascending.
     * Requested format of output:
     * (airport id, distance in KM, airport name).
     *
     * @return airports;
     */
    String getSortedAirportsFromSchipholByDistance() throws IOException;

    /**
     * Reads the given input stream and
     * returns a sorted list of airlines that
     * are flying from Schiphol Airport that day,
     * sorted by total distance of their flights, ascending.
     * Requested format of output:
     * (airline id, distance in KM, airline name).
     *
     * @return airlines;
     */
    List<Airline> getSortedAirlinesFlyingFromSchipholByMileage() throws IOException;
}



