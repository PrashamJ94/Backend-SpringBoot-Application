package com.example.hedgenet_backend.Models;

import java.util.UUID;

public class NewTrade
{
    private String tickerid;

    private UUID fundid;
    private float price;
    private int quantity;

    public String getTickerid() {
        return tickerid;
    }

    public void setTickerid(String tickerid) {
        this.tickerid = tickerid;
    }

    public UUID getFundid() {
        return fundid;
    }

    public void setFundid(UUID fundid) {
        this.fundid = fundid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
