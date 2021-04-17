package com.assignment.coffeemaker;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import com.assignment.coffeemaker.config.ConfigurationLoader;
import com.assignment.coffeemaker.exceptions.InvalidRequestException;
import com.assignment.coffeemaker.model.MachineConfiguration;
import com.assignment.coffeemaker.resources.BeverageController;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class MainApplication {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new InvalidRequestException("File Path required to get Machine Details");
        }
        ConfigurationLoader configurationLoader = new ConfigurationLoader(Path.of(args[0]));
        MachineConfiguration config = configurationLoader.loadConfiguration();
        BeverageController beverageController = BeverageController.getInstance(config);
        Set<String> orderStatus = beverageController.getOrderStatus(config);
        System.out.println("\nItem Status:");
        orderStatus.forEach(System.out::println);
    }
    
}
