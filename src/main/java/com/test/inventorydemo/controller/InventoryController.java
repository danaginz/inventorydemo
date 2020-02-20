package com.test.inventorydemo.controller;

import com.test.inventorydemo.model.InventoryItem;
import com.test.inventorydemo.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryItemService inventoryItemService;

    public InventoryController(InventoryItemService inventoryItemService) {
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryItemService.getAllItems();
    }

    @GetMapping(value = "{id}")
    public InventoryItem readItemDetails(@PathVariable("id") Long itemNo) {
        return inventoryItemService.readItemDetails(itemNo).orElse(null);
    }

    @PutMapping(value = "withdraw/{id}/{quantity}")
    public ResponseEntity<String> withdrawQuantity(@PathVariable("id") Long itemNo,
                                                   @PathVariable("quantity") Long quantity) {
        int response = inventoryItemService.withdrawQuantity(itemNo, quantity);
        if (response == -1) {
            return ResponseEntity.badRequest().body("Cannot withdraw a negative number");
        }
        if (response == -2) {
            return ResponseEntity.badRequest().body("Cannot withdraw more than the current amount");
        }
        if (response == 0) {
            return ResponseEntity.badRequest().body("Inventory item with ID " + itemNo + " doesn't exist");
        }
        return ResponseEntity.ok("Withdrawn successfully!");
    }

    @PutMapping(value = "deposit/{id}/{quantity}")
    public ResponseEntity<String> depositQuantity(@PathVariable("id") Long itemNo,
                                                  @PathVariable("quantity") Long quantity) {
        int response = inventoryItemService.depositQuantity(itemNo, quantity);
        if (response == -1) {
            return ResponseEntity.badRequest().body("Cannot deposit a negative number");
        }
        if (response == 0) {
            return ResponseEntity.badRequest().body("Inventory item with ID " + itemNo + " doesn't exist");
        }
        return ResponseEntity.ok("Deposited successfully!");
    }

    @PostMapping
    public void addItem(@RequestBody InventoryItem inventoryItem) {
        inventoryItemService.addItem(inventoryItem);
    }

    @DeleteMapping(value = "{id}")
    public void deleteItem(@PathVariable("id") Long itemNo) {
        inventoryItemService.deleteItem(itemNo);
    }
}
