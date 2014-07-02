package net.mariyana.flightanalyzer.dao.file;

import net.mariyana.flightanalyzer.dao.AirlinesDAO;
import net.mariyana.flightanalyzer.model.Airline;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AirlinesFileDAO implements AirlinesDAO {


    private static final String AIRLINE_FILENAME = "airlines.txt";


    @Override
    public List<Airline> getAirlines() throws IOException {
        List<Airline> airlines = new ArrayList<Airline>();

        InputStream inputStream = AirlinesFileDAO.class.getResourceAsStream(AIRLINE_FILENAME);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] lineFields = line.split(",");

            String airlineId = StringUtils.strip(lineFields[0], "\"");

            String name = lineFields[1];

            Airline airline = new Airline(airlineId, name);
            airlines.add(airline);
        }
        return airlines;
    }

    @Override
    public Airline getAirlineById(String trimmedAirlineId) throws IOException {

        for (Airline airline : getAirlines()) {
            if (airline.getId().equals(trimmedAirlineId)) {
                return airline;
            }
        }
        return new Airline(Airline.ID_UNKNOWN, Airline.NAME_UNKNOWN);
    }


}
