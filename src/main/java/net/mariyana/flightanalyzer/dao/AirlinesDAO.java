package net.mariyana.flightanalyzer.dao;

import net.mariyana.flightanalyzer.model.Airline;

import java.io.IOException;
import java.util.List;

public interface AirlinesDAO {
    List<Airline> getAirlines() throws IOException;

    Airline getAirlineById(String trimmedAirlineId) throws IOException;
}
