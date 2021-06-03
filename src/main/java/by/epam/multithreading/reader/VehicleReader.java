package main.java.by.epam.multithreading.reader;

import main.java.by.epam.multithreading.exception.VehicleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VehicleReader {
    private static Logger logger = LogManager.getLogger();

    public String parameterReader(String path) throws VehicleException{
        String parameters = "";
        if (path != null) {
            try {
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                URI uri = classLoader.getResource(path).toURI();
                parameters = new String(Files.readAllBytes(Paths.get(uri)));
                logger.info("Data was read");
            } catch (IOException e) {
                throw new VehicleException("File parsing error " + e.getMessage());
            } catch (URISyntaxException w) {
                throw new VehicleException("Error with get resource to uri" + w.getMessage());
            } catch (NullPointerException q) {
                throw new VehicleException("Error with provided path to file" + q.getMessage());
            }
        }
        return parameters;
    }
}
