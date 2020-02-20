package com.test.inventorydemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemNo;

    private String name;
    private Long amount;

    @Column(unique = true)
    private String inventoryCode;

    public InventoryItem() {
    }

    public InventoryItem(@JsonProperty("id") Long itemNo,
                         @JsonProperty("name") String name,
                         @JsonProperty("amount") Long amount,
                         @JsonProperty("inventory_code") String inventoryCode) {
        this.itemNo = itemNo;
        this.name = name;
        this.amount = amount;
        this.inventoryCode = inventoryCode;
    }

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }
}
