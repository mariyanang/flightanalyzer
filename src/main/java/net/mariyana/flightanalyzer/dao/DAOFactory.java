package net.mariyana.flightanalyzer.dao;


import net.mariyana.flightanalyzer.dao.file.AirlinesFileDAO;
import net.mariyana.flightanalyzer.dao.file.AirportsFileDAO;
import net.mariyana.flightanalyzer.dao.file.FlightsFileDAO;

import java.io.InputStream;

public class DAOFactory {

    public FlightsDAO createFlightDAO(InputStream inputStream) {
        return new FlightsFileDAO(inputStream, createAirportDAO(),createAirlineDAO());
    }

    public AirportsDAO createAirportDAO() {
        return new AirportsFileDAO();
    }

    public AirlinesDAO createAirlineDAO() {
        return new AirlinesFileDAO();
    }
}
