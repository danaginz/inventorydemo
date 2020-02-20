package com.test.inventorydemo.service;

import com.test.inventorydemo.model.InventoryItem;
import com.test.inventorydemo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryItemService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> inventoryItemList = new ArrayList<>();
        inventoryRepository.findAll().forEach(inventoryItemList::add);
        return inventoryItemList;
    }

    public Optional<InventoryItem> readItemDetails(Long itemNo) {
        return inventoryRepository.findById(itemNo);
    }

    public int withdrawQuantity(Long itemNo, Long quantity) {
        if (quantity < 0) {
            return -1;
        }
        InventoryItem inventoryItem = inventoryRepository.findById(itemNo).orElse(null);
        if (inventoryItem == null) {
            return 0;
        }
        Long newAmount = inventoryItem.getAmount() - quantity;
        if (newAmount < 0) {
            return -2;
        }
        inventoryItem.setAmount(newAmount);
        inventoryRepository.save(inventoryItem);
        return 1;
    }

    public int depositQuantity(Long itemNo, Long quantity) {
        if (quantity < 0) {
            return -1;
        }
        InventoryItem inventoryItem = inventoryRepository.findById(itemNo).orElse(null);
        if (inventoryItem == null) {
            return 0;
        }
        Long newAmount = inventoryItem.getAmount() + quantity;
        inventoryItem.setAmount(newAmount);
        inventoryRepository.save(inventoryItem);
        return 1;
    }

    public void addItem(InventoryItem inventoryItem) {
        inventoryRepository.save(inventoryItem);
    }

    public void deleteItem(Long itemNo) {
        inventoryRepository.deleteById(itemNo);
    }
}
