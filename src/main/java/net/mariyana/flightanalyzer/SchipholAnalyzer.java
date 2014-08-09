package net.mariyana.flightanalyzer;


import net.mariyana.flightanalyzer.dao.AirlinesDAO;
import net.mariyana.flightanalyzer.dao.AirportsDAO;
import net.mariyana.flightanalyzer.dao.DAOFactory;
import net.mariyana.flightanalyzer.dao.FlightsDAO;
import net.mariyana.flightanalyzer.model.Airline;
import net.mariyana.flightanalyzer.model.Airport;
import net.mariyana.flightanalyzer.model.Flight;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SchipholAnalyzer implements FlightAnalyzer {

    private final DAOFactory daoFactory = new DAOFactory();
    private FlightsDAO flightDAO = null;
    private AirportsDAO airportDAO = null;
    private AirlinesDAO airlineDAO = null;

    private static InputStream inputStream = null;

    @Override
    public void configure(InputStream inputStream) {
        this.inputStream = inputStream;
        flightDAO = daoFactory.createFlightDAO(inputStream);
        airportDAO = daoFactory.createAirportDAO();
        airlineDAO = daoFactory.createAirlineDAO();
    }

    private void validateInput() {
        if (inputStream == null) {
            throw new RuntimeException();
        }
    }

    @Override
    public int getTotalDepartureFlightsFromSchiphol() throws IOException {
        validateInput();

        int totalFlights = 0;
        for (Flight flight : flightDAO.getFlights()) {
            if (flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL)) {
                totalFlights++;
            }
        }
        return totalFlights;
    }

    @Override
    public int getTotalAirportsReachableFromSchiphol() throws IOException {
        List<String> airportsReachableFromSchiphol = new ArrayList<String>();

        for (Flight flight : flightDAO.getFlights()) {
            if (flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL)
                    && !airportsReachableFromSchiphol.contains(flight.getArrivalAirport().getId())) {
                airportsReachableFromSchiphol.add(flight.getArrivalAirport().getId());
            }
        }
        return airportsReachableFromSchiphol.size();
    }

    @Override
    public int getTotalAirlinesFlyingFromSchiphol() throws IOException {
        List<String> airlinesFlyingFromSchiphol = new ArrayList<String>();

        for (Flight flight : flightDAO.getFlights()) {
            if (flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL)
                    && !airlinesFlyingFromSchiphol.contains(flight.getAirline().getId())) {
                airlinesFlyingFromSchiphol.add(flight.getAirline().getId());
            }
        }
        return airlinesFlyingFromSchiphol.size();
    }

    @Override
    public int getAverageDistanceOfFlightsFromSchiphol() throws IOException {
        List<Integer> allFlightsDistances = new ArrayList<Integer>();
        int totalSumKm = 0;

        for (Flight flight : flightDAO.getFlights()) {
            if (!flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL) ||
                    flight.getDepartureAirport().getId().equals(Airport.ID_UNKNOWN)
                    || flight.getArrivalAirport().getId().equals(Airport.ID_UNKNOWN)) {
                continue;
            }
            int km = flight.getDistance();
            allFlightsDistances.add(km);

            totalSumKm += km;
        }
        return totalSumKm / allFlightsDistances.size();
    }

    @Override
    public String getSortedAirportsFromSchipholByDistance() throws IOException {
        List<Flight> flightsFromSchiphol = new ArrayList<Flight>();

        for (Flight flight : flightDAO.getFlights()) {
            if (flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL)) {
                flightsFromSchiphol.add(flight);
            }
        }

        Collections.sort(flightsFromSchiphol, Flight.BY_DISTANCE_ASC);

        String result = "";
        for (Flight flight : flightsFromSchiphol) {
            result += flight.getArrivalAirport().getId() + ", "
                    + flight.getDistance() + ", "
                    + flight.getArrivalAirport().getName() + System.getProperty("line.separator");
        }
        return result;
    }

    @Override
    public List<Airline> getSortedAirlinesFlyingFromSchipholByMileage() throws IOException {
        ArrayList<Airline> result = new ArrayList<Airline>();

        for (Flight flight : flightDAO.getFlights()) {
            if (!flight.getDepartureAirport().getId().equals(Airport.ID_SCHIPHOL)) {
                continue;
            }

            if (!result.contains(flight.getAirline())) {
                result.add(flight.getAirline());
            }
            float currentAirlineMileage = flight.getAirline().getMileage();
            flight.getAirline().setMileage(currentAirlineMileage + flight.getDistance());
        }

        Collections.sort(result, Airline.BY_DISTANCE_ASC_NAME_DESC);

        //      Manual sorting mostly used for sorting primitives
        //        for (Map.Entry<Airline, Integer> entry : map.entrySet()) {
        //            Airline newAirline = entry.getKey();
        //            Integer newMileage = entry.getValue();
        //
        //            int position = 0;
        //            for (Airline airline : result) {
        //                position = result.size();
        //                Integer mileage = map.get(airline);
        //                if (mileage > newMileage) {
        //                    position = result.indexOf(airline);
        //                    break;
        //                }
        //            }
        //            result.add(position, newAirline);
        //        }

        for (Airline airline : result) {
            System.out.println(airline.getId() + ", " + airline.getMileage() + ", " + airline.getName());
        }

        return result;
    }
}
