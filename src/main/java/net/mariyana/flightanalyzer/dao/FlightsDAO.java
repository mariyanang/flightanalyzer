package net.mariyana.flightanalyzer.dao;

import net.mariyana.flightanalyzer.model.Flight;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FlightsDAO {
    List<Flight> getFlights() throws IOException;
}
