package com.assignment.coffeemaker.resources;

import java.util.Objects;
import java.util.Set;

import com.assignment.coffeemaker.exceptions.InvalidRequestException;
import com.assignment.coffeemaker.model.MachineConfiguration;
import com.assignment.coffeemaker.service.BeverageService;

/**
 * @author shubham sharma
 *         <p>
 *         17/04/21
 */
public class BeverageController {
    private static final BeverageController beverageController = new BeverageController();
    
    private static BeverageService beverageService = null;
    private BeverageController(){}
    
    public static BeverageController getInstance(MachineConfiguration config) {
        if(Objects.isNull(beverageService)) {
            beverageService = BeverageService.getInstance(config);
        }
        return beverageController;
    }
    
    public Set<String> getOrderStatus(MachineConfiguration machineConfiguration){
        Objects.requireNonNull(machineConfiguration.getMachine().getQuantities());
        
        if(machineConfiguration.getMachine().getBeverages().isEmpty()) {
            throw new InvalidRequestException("No Order Placed, Kindly place some order first");
        }
        return beverageService.checkOrderAvailability(machineConfiguration);
    }
}
