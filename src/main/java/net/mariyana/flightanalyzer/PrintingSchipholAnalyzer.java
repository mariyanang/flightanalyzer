package net.mariyana.flightanalyzer;

import net.mariyana.flightanalyzer.model.Airline;

import java.io.IOException;
import java.io.PrintStream;

public class PrintingSchipholAnalyzer extends SchipholAnalyzer {

    public void printSortedAirlinesByMileage(PrintStream ps) throws IOException {
        for (Airline airline : getSortedAirlinesFlyingFromSchipholByMileage()) {
            ps.println(airline.getId() + ", " + airline.getMileage() + ", " + airline.getName());
        }
    }
}
