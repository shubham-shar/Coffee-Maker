package com.assignment.coffeemaker;

import static org.junit.jupiter.api.Assertions.fail;

import com.assignment.coffeemaker.exceptions.AppException;
import com.assignment.coffeemaker.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author shubham sharma
 *         <p>
 *         17/04/21
 */
class MainApplicationTest {
    
    @Test
    void runCoffeeMakerProgram() {
        MainApplication.main(new String[]{"src/test/config/machine-config.json"});
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"src/test/config/machine-config-no-item-left.json", "src/test/config/wrong-file.json",
                            "src/test/config/machine-config-no-order-placed.json"})
    void runCoffeeMakerProgramForException(String input) {
        try {
            MainApplication.main(new String[]{input});
            fail("Not exception thrown, even when expected");
        } catch (InvalidRequestException | AppException e) {
            System.out.println("exception thrown correctly with message: " + e.getMessage());
        }
    }
}
