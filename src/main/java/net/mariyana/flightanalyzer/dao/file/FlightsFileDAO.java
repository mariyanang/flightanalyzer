package net.mariyana.flightanalyzer.dao.file;

import net.mariyana.flightanalyzer.dao.AirlinesDAO;
import net.mariyana.flightanalyzer.dao.AirportsDAO;
import net.mariyana.flightanalyzer.dao.FlightsDAO;
import net.mariyana.flightanalyzer.model.Airline;
import net.mariyana.flightanalyzer.model.Airport;
import net.mariyana.flightanalyzer.model.Flight;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FlightsFileDAO implements FlightsDAO {

    private InputStream inputStream;
    private AirportsDAO airportsDAO;
    private AirlinesDAO airlinesDAO;

    private List<Flight> flightsCache;

    public FlightsFileDAO(InputStream inputStream, AirportsDAO airportsDAO, AirlinesDAO airlinesDAO) {
        this.inputStream = inputStream;
        this.airportsDAO = airportsDAO;
        this.airlinesDAO = airlinesDAO;
    }

    public List<Flight> getFlights() throws IOException {
        if (flightsCache != null) {
//            return flightsCache;

            List<Flight> newFlights = new ArrayList<Flight>();
            //Copy-by-value
            newFlights.addAll(flightsCache);
            return newFlights;
        }

        List<Flight> tempFlights = new ArrayList<Flight>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
                Charset.forName("UTF-8")));
        String line;

        while ((line = br.readLine()) != null) {
            String[] lineFields = line.split(",");

            Integer id = null;
            try {
                id = Integer.parseInt(lineFields[1]);
            } catch (NumberFormatException e) {
                continue;
            }

            String trimmedDepartureAirportId = StringUtils.strip(lineFields[2], "\"");
            Airport departureAirport = airportsDAO.getAirportById(trimmedDepartureAirportId);
            String trimmedArrivalAirportId = StringUtils.strip(lineFields[3], "\"");
            Airport arrivalAirport = airportsDAO.getAirportById(trimmedArrivalAirportId);
            String trimmedAirlineId = StringUtils.strip(lineFields[0], "\"");
            Airline airline = airlinesDAO.getAirlineById(trimmedAirlineId);

            Flight flight = new Flight(id, departureAirport, arrivalAirport, airline);
            tempFlights.add(flight);
        }

        // Copy-by-reference
        flightsCache = tempFlights;
//        return flights;

        List<Flight> newFlights = new ArrayList<Flight>();
        //Copy-by-value
        newFlights.addAll(flightsCache);
        return newFlights;
    }
}
