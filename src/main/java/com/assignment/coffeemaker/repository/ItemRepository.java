package com.assignment.coffeemaker.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import com.assignment.coffeemaker.model.BeverageStatus;

/**
 * @author shubham sharma
 *         <p>
 *         17/04/21
 */
public class ItemRepository {
    private static final ItemRepository itemRepository = new ItemRepository();
    
    private static Map<String, Integer> itemQuantities = Collections.synchronizedMap(new LinkedHashMap<>());
    
    private ItemRepository() {
    }
    
    public static ItemRepository getInstance() {
        return itemRepository;
    }
    
    public void saveQuantities(Map<String, Integer> quantities) {
        System.out.println("Saving item quantities in the inventory");
        quantities.forEach((key, value) -> {
            itemQuantities.compute(key, (keyId, itemCount) -> {
                if (Objects.nonNull(itemCount)) {
                    return itemCount + value;
                }
                return value;
            });
        });
        
        System.out.println("Saved item quantities in the inventory");
        
    }
    
    public BeverageStatus itemStatus(String item, Integer quantity) {
        AtomicReference<BeverageStatus> beverageStatus = new AtomicReference<>(BeverageStatus.NOT_AVAILABLE);
        itemQuantities.compute(item, (keyId, itemCount) -> {
            if (Objects.nonNull(itemCount)) {
                if (itemCount >= quantity) {
                    beverageStatus.set(BeverageStatus.AVAILABLE);
                    return itemCount;
                } else {
                    beverageStatus.set(BeverageStatus.NOT_SUFFICIENT);
                    return itemCount;
                }
            }
            return itemCount;
        });
        return beverageStatus.get();
    }
    
    public void useItems(String item, Integer quantity) {
        itemQuantities.compute(item, (keyId, itemCount) -> {
            if (Objects.nonNull(itemCount) && itemCount >= quantity) {
                return itemCount - quantity;
            }
            return itemCount;
        });
    }
    
    public static Map<String, Integer> getItemQuantities() {
        return itemQuantities;
    }
}
