package com.assignment.coffeemaker.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.ReentrantLock;

import com.assignment.coffeemaker.model.BeverageStatus;
import com.assignment.coffeemaker.repository.ItemRepository;

/**
 * @author shubham sharma
 *         <p>
 *         17/04/21
 */
public class BeverageMakerService extends RecursiveTask<String> {
    public static final String CANNOT_BE_PREPARED_BECAUSE = " cannot be prepared because";
    public static final String ITEMS = " items ";
    public static final String ITEM = " item ";
    public static final String IS = " is ";
    public static final String ARE = " are ";
    public static final String NOT_AVAILABLE = "not available";
    public static final String NOT_SUFFICIENT = "not sufficient";
    public static final String IS_PREPARED = " is prepared";
    private static final ItemRepository itemRepository = ItemRepository.getInstance();
    
    private final String beverageName;
    private final Map<String, Integer> beverage;
    private Set<String> response;
    private ReentrantLock lock;
    
    public BeverageMakerService(Map<String, Integer> beverage, String beverageName, Set<String> response,
            ReentrantLock lock) {
        this.beverage = beverage;
        this.beverageName = beverageName;
        this.response = response;
        this.lock = lock;
    }
    
    @Override
    protected String compute() {
        lock.lock();
        Map<BeverageStatus, Set<String>> itemStatus = new HashMap<>();
        beverage.forEach((key, value)-> {
                    BeverageStatus beverageStatus = itemRepository.itemStatus(key, value);
                    if(itemStatus.containsKey(beverageStatus)) {
                        itemStatus.get(beverageStatus).add(key);
                    } else {
                        Set<String> items = new HashSet<>();
                        items.add(key);
                        itemStatus.put(beverageStatus, items);
                    }
                }
        );
        if (!itemStatus.containsKey(BeverageStatus.NOT_AVAILABLE)
                || !itemStatus.containsKey(BeverageStatus.NOT_SUFFICIENT)) {
            beverage.forEach(itemRepository::useItems);
        }
        lock.unlock();
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(beverageName);
    
        if (itemStatus.containsKey(BeverageStatus.NOT_AVAILABLE)) {
            stringBuilder.append(CANNOT_BE_PREPARED_BECAUSE);
            if(itemStatus.get(BeverageStatus.NOT_AVAILABLE).size() > 1) {
                stringBuilder.append(ITEMS);
                Set<String> strings = itemStatus.get(BeverageStatus.NOT_AVAILABLE);
                stringBuilder.append(String.join(", ", strings));
                stringBuilder.append(ARE);
                stringBuilder.append(NOT_AVAILABLE);
            } else {
                stringBuilder.append(ITEM);
                Set<String> strings = itemStatus.get(BeverageStatus.NOT_AVAILABLE);
                stringBuilder.append(String.join(", ", strings));
                stringBuilder.append(IS);
                stringBuilder.append(NOT_AVAILABLE);
            }
            this.response.add(stringBuilder.toString());
            return stringBuilder.toString();
        }
    
        if (itemStatus.containsKey(BeverageStatus.NOT_SUFFICIENT)) {
            stringBuilder.append(CANNOT_BE_PREPARED_BECAUSE);
            if(itemStatus.get(BeverageStatus.NOT_SUFFICIENT).size() > 1) {
                stringBuilder.append(ITEMS);
                Set<String> strings = itemStatus.get(BeverageStatus.NOT_SUFFICIENT);
                stringBuilder.append(String.join(", ", strings));
                stringBuilder.append(ARE);
                stringBuilder.append(NOT_SUFFICIENT);
            } else {
                stringBuilder.append(ITEM);
                Set<String> strings = itemStatus.get(BeverageStatus.NOT_SUFFICIENT);
                stringBuilder.append(String.join(", ", strings));
                stringBuilder.append(IS);
                stringBuilder.append(NOT_SUFFICIENT);
            }
            this.response.add(stringBuilder.toString());
            return stringBuilder.toString();
        }
        
        stringBuilder.append(IS_PREPARED);
        this.response.add(stringBuilder.toString());
        return stringBuilder.toString();
    }
    
}
