package com.assignment.coffeemaker.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

import com.assignment.coffeemaker.exceptions.AppException;
import com.assignment.coffeemaker.model.MachineConfiguration;
import com.assignment.coffeemaker.repository.ItemRepository;

/**
 * @author shubham sharma
 *         <p>
 *         17/04/21
 */
public class BeverageService {
    private static BeverageService beverageService = null;
    
    private static final ItemRepository itemRepository = ItemRepository.getInstance();
    
    private ConcurrentSkipListSet<String> response = new ConcurrentSkipListSet<>();
    private final ForkJoinPool pool;
    private final ReentrantLock lock = new ReentrantLock();
    
    private BeverageService(int threadCount){
        this.pool = new ForkJoinPool(threadCount);
    }
    
    public static BeverageService getInstance(MachineConfiguration config) {
        if(Objects.isNull(beverageService)) {
            beverageService = new BeverageService(config.getMachine().getOutlets().getCount());
        }
        return beverageService;
    }
    
    public Set<String> checkOrderAvailability(MachineConfiguration config) {
        Map<String, Integer> quantities = config.getMachine().getQuantities();
        Objects.requireNonNull(quantities);
        
        if(quantities.isEmpty()) {
            throw new AppException("Apologies, we are not ready to serve yet!!!");
        }
        itemRepository.saveQuantities(quantities);
        
        config.getMachine().getBeverages().forEach((key, value) -> {
            pool.invoke(new BeverageMakerService(value, key, response, lock));
        });
        
        return response;
    }
}
