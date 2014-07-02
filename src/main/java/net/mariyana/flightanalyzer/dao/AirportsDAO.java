package net.mariyana.flightanalyzer.dao;

import net.mariyana.flightanalyzer.model.Airport;

import java.io.IOException;
import java.util.List;

public interface AirportsDAO {

    List<Airport> getAirports() throws IOException;

    Airport getAirportById(String id) throws IOException;
}
