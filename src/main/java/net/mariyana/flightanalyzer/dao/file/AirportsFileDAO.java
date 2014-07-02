package net.mariyana.flightanalyzer.dao.file;

import net.mariyana.flightanalyzer.dao.AirportsDAO;
import net.mariyana.flightanalyzer.model.Airport;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AirportsFileDAO implements AirportsDAO {

    private static final String AIRPORTS_FILENAME = "airports.txt";

    @Override
    public List<Airport> getAirports() throws IOException {
        List<Airport> airports = new ArrayList<Airport>();

        InputStream inputStream = AirportsFileDAO.class.getResourceAsStream(AIRPORTS_FILENAME);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
                Charset.forName("UTF-8")));
        String line;

        while ((line = br.readLine()) != null) {
            String[] lineFields = line.split(",");

            String airportId = StringUtils.strip(lineFields[0], "\"");

            Float latitude = null;
            Float longitude = null;
            try {
                latitude = Float.parseFloat(lineFields[1]);
                longitude = Float.parseFloat(lineFields[2]);
            } catch (NumberFormatException e) {
                continue;
            }

            String name = lineFields[3];
            String city = lineFields[4];
            String countryId = lineFields[5];

            Airport airport = new Airport(airportId, latitude, longitude, name, city, countryId);
            airports.add(airport);
        }
        return airports;
    }

    @Override
    public Airport getAirportById(String id) throws IOException {
        for (Airport airport : getAirports()) {
            if (airport.getId().equals(id)) {
                return airport;
            }
        }
        return new Airport(Airport.ID_UNKNOWN, 0.0f, 0.0f, "", "", "");
    }

    public List<Airport> getAirportsSortedByIdAsc() throws IOException {
        List<Airport> airports = getAirports();
        Collections.sort(airports);
        return airports;
    }

    public List<Airport> getAirportsSortedByIdDesc() throws IOException {
        List<Airport> airports = getAirports();
        Collections.sort(airports, Airport.BY_ID_DESC);
        return airports;
    }
}
