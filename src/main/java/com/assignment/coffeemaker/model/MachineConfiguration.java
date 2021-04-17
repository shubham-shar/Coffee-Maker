package com.assignment.coffeemaker.model;

import java.util.Objects;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class MachineConfiguration {
    
    private Machine machine;
    
    public Machine getMachine() {
        return machine;
    }
    
    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    @Override
    public String toString() {
        return "MachineConfiguration{" + "machine=" + machine + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MachineConfiguration)) {
            return false;
        }
        MachineConfiguration that = (MachineConfiguration) o;
        return machine.equals(that.machine);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(machine);
    }
}
