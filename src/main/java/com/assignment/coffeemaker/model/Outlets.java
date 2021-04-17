package com.assignment.coffeemaker.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author shubham sharma
 *         <p>
 *         16/04/21
 */
public class Outlets {
    @JsonProperty("count_n")
    private Integer count;
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    @Override
    public String toString() {
        return "Outlets{" + "count=" + count + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Outlets)) {
            return false;
        }
        Outlets outlets = (Outlets) o;
        return Objects.equals(count, outlets.count);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
