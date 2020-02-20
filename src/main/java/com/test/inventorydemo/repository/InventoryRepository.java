package com.test.inventorydemo.repository;

import com.test.inventorydemo.model.InventoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryItem, Long> {

}
