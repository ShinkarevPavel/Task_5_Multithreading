package main.java.by.epam.multithreading.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class VehicleParser {
    private static Logger logger = LogManager.getLogger();
    private final String PARAMETER_DELIMITER = "\\s+";
    private List<Double> parameters;

    public VehicleParser() {
        this.parameters = new ArrayList<>();
    }

    public List<Double> dataParser(String content) {
        for (String digit : content.split(PARAMETER_DELIMITER)) {
            parameters.add(Double.parseDouble(digit));
        }
        logger.info("Parameters were parsed");
        return parameters;
    }
}
