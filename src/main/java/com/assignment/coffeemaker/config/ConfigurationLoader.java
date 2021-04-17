package com.assignment.coffeemaker.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Objects;

import com.assignment.coffeemaker.exceptions.InvalidRequestException;
import com.assignment.coffeemaker.model.MachineConfiguration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class ConfigurationLoader {
    private static ConfigurationLoader configurationLoader = null;
    
    private final Path path;
    
    private ConfigurationLoader(Path path){
        this.path = path;
    }
    
    public static ConfigurationLoader getInstance(Path path) {
        if(Objects.isNull(configurationLoader)) {
            configurationLoader = new ConfigurationLoader(path);
        }
        return configurationLoader;
    }
    
    /**
     * Loads machine Configuration from this {@link ConfigurationLoader}'s path
     *
     * @return the loaded {@link MachineConfiguration}.
     */
    public MachineConfiguration loadConfiguration() {
    
        try(Reader reader = new FileReader(this.path.toFile())){
            return read(reader);
        } catch (FileNotFoundException ex) {
            throw new InvalidRequestException("File Path provided is not valid, Please check once");
        } catch (IOException exception) {
            throw new RuntimeException("Something went wrong in the application " + exception);
        }
    }
    
    /**
     * Loads machine configuration from the given reader.
     *
     * @param reader a Reader pointing to a JSON string that contains machine configuration.
     * @return a crawler configuration
     */
    public static MachineConfiguration read(Reader reader) throws IOException {
        Objects.requireNonNull(reader);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return objectMapper.readValue(reader, MachineConfiguration.class);
    }
}
