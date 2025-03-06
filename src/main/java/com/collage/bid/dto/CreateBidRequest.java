package com.collage.bid.dto;

import com.collage.bid.model.Bid;
import com.collage.bid.model.Item;

import java.util.List;

public class CreateBidRequest {

    private Bid bid;
    private List<Item> items;

    // Getters and setters
    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}