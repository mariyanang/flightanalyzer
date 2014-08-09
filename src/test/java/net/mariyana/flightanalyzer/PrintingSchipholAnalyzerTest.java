package net.mariyana.flightanalyzer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class PrintingSchipholAnalyzerTest {
    public static final String TEST_RESOURCE_FILENAME = "flights_15_april.txt";
    private static final String EXPECTED_SORTED_AIRLINES_BY_MILEAGE = "WX, 208.0, \"Cityjet\"" + System.getProperty("line.separator") +
            "CJ, 208.0, \"BA CityFlyer\"" + System.getProperty("line.separator") +
            "BA, 229.0, \"British Airways\"" + System.getProperty("line.separator") +
            "EW, 236.0, \"Eurowings\"" + System.getProperty("line.separator") +
            "AF, 247.0, \"Air France\"" + System.getProperty("line.separator") +
            "BE, 274.0, \"FlyBE\"" + System.getProperty("line.separator") +
            "LS, 287.0, \"Jet2.com\"" + System.getProperty("line.separator") +
            "YS, 288.0, \"Regional Compagnie Aerienne Europeene\"" + System.getProperty("line.separator") +
            "WA, 324.0, \"KLM Cityhopper\"" + System.getProperty("line.separator") +
            "U2, 347.0, \"easyJet\"" + System.getProperty("line.separator") +
            "LX, 375.0, \"SWISS\"" + System.getProperty("line.separator") +
            "SX, 392.0, \"Sky Work Airlines\"" + System.getProperty("line.separator") +
            "SK, 393.0, \"SAS - Scandinavian Airlines\"" + System.getProperty("line.separator") +
            "KF, 393.0, \"Blue1\"" + System.getProperty("line.separator") +
            "CL, 412.0, \"Lufthansa Cityline\"" + System.getProperty("line.separator") +
            "IQ, 412.0, \"Augsburg Airways\"" + System.getProperty("line.separator") +
            "OK, 438.0, \"Czech Airlines\"" + System.getProperty("line.separator") +
            "EI, 465.0, \"Aer Lingus\"" + System.getProperty("line.separator") +
            "KZ, 494.0, \"Nippon Cargo\"" + System.getProperty("line.separator") +
            "K2, 582.0, \"EuroLot\"" + System.getProperty("line.separator") +
            "VO, 595.0, \"Tyrolean Airways\"" + System.getProperty("line.separator") +
            "DY, 596.0, \"Norwegian Air Shuttle\"" + System.getProperty("line.separator") +
            "JP, 605.0, \"Adria Airways\"" + System.getProperty("line.separator") +
            "OU, 683.0, \"Croatia Airlines\"" + System.getProperty("line.separator") +
            "LO, 684.0, \"LOT - Polish Airlines\"" + System.getProperty("line.separator") +
            "KE, 716.0, \"Korean Air\"" + System.getProperty("line.separator") +
            "AZ, 806.0, \"Alitalia\"" + System.getProperty("line.separator") +
            "BT, 828.0, \"airBaltic\"" + System.getProperty("line.separator") +
            "JU, 876.0, \"Jat Airways\"" + System.getProperty("line.separator") +
            "UX, 908.0, \"Air Europa\"" + System.getProperty("line.separator") +
            "OV, 913.0, \"Estonian Air\"" + System.getProperty("line.separator") +
            "AY, 945.0, \"Finnair\"" + System.getProperty("line.separator") +
            "B2, 967.0, \"Belavia\"" + System.getProperty("line.separator") +
            "NI, 991.0, \"Portugalia Airlines\"" + System.getProperty("line.separator") +
            "VY, 1002.0, \"Vueling\"" + System.getProperty("line.separator") +
            "FB, 1089.0, \"Bulgaria Air\"" + System.getProperty("line.separator") +
            "RO, 1107.0, \"Tarom\"" + System.getProperty("line.separator") +
            "PS, 1130.0, \"Ukraine International\"" + System.getProperty("line.separator") +
            "TP, 1147.0, \"TAP-Air Portugal\"" + System.getProperty("line.separator") +
            "KM, 1232.0, \"Air Malta\"" + System.getProperty("line.separator") +
            "AT, 1244.0, \"Royal Air Maroc\"" + System.getProperty("line.separator") +
            "3O, 1259.0, \"Air Arabia Maroc\"" + System.getProperty("line.separator") +
            "FI, 1267.0, \"Icelandair\"" + System.getProperty("line.separator") +
            "RU, 1332.0, \"AirBridge Cargo\"" + System.getProperty("line.separator") +
            "SU, 1332.0, \"Aeroflot\"" + System.getProperty("line.separator") +
            "TK, 1373.0, \"Turkish Airlines\"" + System.getProperty("line.separator") +
            "PC, 1649.0, \"Pegasus Airlines\"" + System.getProperty("line.separator") +
            "CY, 1858.0, \"Cyprus Airways\"" + System.getProperty("line.separator") +
            "HV, 2003.0, \"Transavia\"" + System.getProperty("line.separator") +
            "MS, 2042.0, \"Egyptair\"" + System.getProperty("line.separator") +
            "LY, 2058.0, \"El Al\"" + System.getProperty("line.separator") +
            "KC, 2094.0, \"Air Astana\"" + System.getProperty("line.separator") +
            "RJ, 2113.0, \"Royal Jordanian\"" + System.getProperty("line.separator") +
            "EK, 3211.0, \"Emirates\"" + System.getProperty("line.separator") +
            "EY, 3226.0, \"Etihad Airways\"" + System.getProperty("line.separator") +
            "PK, 3624.0, \"Pakistan International Airlines\"" + System.getProperty("line.separator") +
            "LH, 3624.0, \"Lufthansa\"" + System.getProperty("line.separator") +
            "K4, 3647.0, \"Kalitta Air\"" + System.getProperty("line.separator") +
            "US, 3726.0, \"US Airways\"" + System.getProperty("line.separator") +
            "KQ, 4149.0, \"Kenya Airways\"" + System.getProperty("line.separator") +
            "CX, 4262.0, \"Cathay Pacific Airways\"" + System.getProperty("line.separator") +
            "KL, 4390.0, \"KLM\"" + System.getProperty("line.separator") +
            "DL, 4390.0, \"Delta Air Lines\"" + System.getProperty("line.separator") +
            "MP, 4453.0, \"Martinair\"" + System.getProperty("line.separator") +
            "OR, 4510.0, \"Arkefly\"" + System.getProperty("line.separator") +
            "PY, 4675.0, \"Surinam Airways\"" + System.getProperty("line.separator") +
            "CA, 4940.0, \"Air China International\"" + System.getProperty("line.separator") +
            "UA, 5001.0, \"United Airlines\"" + System.getProperty("line.separator") +
            "CK, 5536.0, \"China Cargo\"" + System.getProperty("line.separator") +
            "CZ, 5679.0, \"China Southern\"" + System.getProperty("line.separator") +
            "CI, 5720.0, \"China Airlines\"" + System.getProperty("line.separator") +
            "MH, 6360.0, \"Malaysia Airline\"" + System.getProperty("line.separator") +
            "SQ, 6532.0, \"Singapore Airlines\"" + System.getProperty("line.separator");

    private PrintingSchipholAnalyzer printingSchipholAnalyzer;
    private InputStream testInputStream = null;

    @Before
    public void before() {
        testInputStream = FlightAnalyzerTest.class.getResourceAsStream(TEST_RESOURCE_FILENAME);
        Assert.assertNotNull(testInputStream);

        printingSchipholAnalyzer = new PrintingSchipholAnalyzer();
        printingSchipholAnalyzer.configure(testInputStream);
    }
    @After
    public void after() throws IOException {
        testInputStream.close();
    }

    @Test
    public void printSortedAirlinesByMileageTest() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        printingSchipholAnalyzer.printSortedAirlinesByMileage(ps);
        Assert.assertEquals(out.toString(),EXPECTED_SORTED_AIRLINES_BY_MILEAGE);
    }
}
