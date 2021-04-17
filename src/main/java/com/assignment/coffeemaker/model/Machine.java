package com.assignment.coffeemaker.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class Machine {
    private Outlets outlets;
    
    @JsonProperty("total_items_quantity")
    private Map<String, Integer> quantities;
    
    private LinkedHashMap<String, Map<String, Integer>> beverages;
    
    public Outlets getOutlets() {
        return outlets;
    }
    
    public void setOutlets(Outlets outlets) {
        this.outlets = outlets;
    }
    
    public Map<String, Integer> getQuantities() {
        return quantities;
    }
    
    public void setQuantities(Map<String, Integer> quantities) {
        this.quantities = quantities;
    }
    
    public LinkedHashMap<String, Map<String, Integer>> getBeverages() {
        return beverages;
    }
    
    public void setBeverages(LinkedHashMap<String, Map<String, Integer>> beverages) {
        this.beverages = beverages;
    }
    
    @Override
    public String toString() {
        return "Machine{" + "outlets=" + outlets + ", quantities=" + quantities + ", beverages=" + beverages + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Machine)) {
            return false;
        }
        Machine machine = (Machine) o;
        return Objects.equals(outlets, machine.outlets) && Objects.equals(quantities, machine.quantities) && Objects
                .equals(beverages, machine.beverages);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(outlets, quantities, beverages);
    }
}
