package net.mariyana.flightanalyzer;

import net.mariyana.flightanalyzer.model.Airline;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FlightAnalyzerTest {

    public static final String TEST_RESOURCE_FILENAME = "flights_15_april.txt";
    public static final int EXPECTED_TOTAL_FLIGHTS_FROM_SCHIPHOL = 605;
    public static final int EXPECTED_TOTAL_REACHABLE_AIRPORTS = 189;
    public static final int EXPECTED_TOTAL_AIRLINES_FLYING_FROM_SCHIPHOL = 73;
    private static final int EXPECTED_AVERAGE_DISTANCE_OF_FLIGHTS_FROM_SCHIPHOL = 1241;
    private static final String EXPECTED_SORTED_AIRPORTS_BY_DISTANCE = "RTM, 28, \"Rotterdam The Hague Airport\"\n" +
            "BRU, 98, \"Brussels Airport\"";

    private InputStream testInputStream = null;
    private FlightAnalyzer flightAnalyzer = null;

    @Before
    public void before() {

        testInputStream = FlightAnalyzerTest.class.getResourceAsStream(TEST_RESOURCE_FILENAME);
        Assert.assertNotNull(testInputStream);

        flightAnalyzer = new SchipholAnalyzer();
        flightAnalyzer.configure(testInputStream);

    }

    @After
    public void after() throws IOException {
        testInputStream.close();
    }

    @Test
    public void testTotalDepartureFlightsFromSchiphol() throws IOException {
        int totalDepartureFlightsFromSchiphol = flightAnalyzer.getTotalDepartureFlightsFromSchiphol();
        Assert.assertEquals(EXPECTED_TOTAL_FLIGHTS_FROM_SCHIPHOL, totalDepartureFlightsFromSchiphol);
    }

    @Test
    public void testTotalAirportsReachableFromSchiphol() throws IOException {
        int totalAirportsReachableFromSchiphol = flightAnalyzer.getTotalAirportsReachableFromSchiphol();
        Assert.assertEquals(EXPECTED_TOTAL_REACHABLE_AIRPORTS, totalAirportsReachableFromSchiphol);
    }

    @Test
    public void testTotalAirlinesFlyingFromSchiphol() throws IOException {
        int totalAirlinesFLyingFromSchiphol = flightAnalyzer.getTotalAirlinesFlyingFromSchiphol();
        Assert.assertEquals(EXPECTED_TOTAL_AIRLINES_FLYING_FROM_SCHIPHOL, totalAirlinesFLyingFromSchiphol);
    }

    @Test
    public void testAverageDistanceOfFlightsFromSchiphol() throws IOException {
        int averageDistanceOfFlights = flightAnalyzer.getAverageDistanceOfFlightsFromSchiphol();
        Assert.assertEquals(EXPECTED_AVERAGE_DISTANCE_OF_FLIGHTS_FROM_SCHIPHOL, averageDistanceOfFlights);
    }

    @Test
    public void testSortedAirportsFromSchipholByDistance() throws IOException {
        String flightsFromSchiphol = flightAnalyzer.getSortedAirportsFromSchipholByDistance();
        Assert.assertTrue(flightsFromSchiphol.contains(EXPECTED_SORTED_AIRPORTS_BY_DISTANCE));
    }

    @Test
    public void testSortedAirlinesFlyingFromSchipholByMileage() throws IOException {
        List<Airline> sortedAirlinesFromSchiphol = flightAnalyzer.getSortedAirlinesFlyingFromSchipholByMileage();
        Airline previous = null;
        for (Airline airline : sortedAirlinesFromSchiphol) {
            if (previous != null) {
                Assert.assertTrue(previous.getMileage() <= airline.getMileage());
                if (previous.getMileage() == airline.getMileage()) {
                    Assert.assertTrue(previous.getName().compareTo(airline.getName()) > 0);
                }
            }
            previous = airline;
        }
    }

    @Test
    public void testAirlineEquals() {
        Airline a1 = new Airline("id1", "Dummy1");
        Airline a2 = new Airline("id1", "Dummy1");
        Assert.assertEquals(a1, a2);
    }
}
